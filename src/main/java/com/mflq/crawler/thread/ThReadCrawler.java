package com.mflq.crawler.thread;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import com.google.common.io.Files;
import com.mflq.crawler.models.bean.CrawlerRequest;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class ThReadCrawler extends WebCrawler {

	/* Variable de clase que inicializa la url */
	private CrawlerRequest crawlerRequest;

	/*
	 * Creemos nuestro rastreador ampliando WebCrawler en nuestra clase de
	 * rastreador y definiendo un patrón para excluir ciertos tipos de archivos:
	 */
	private Pattern FILTERS;

	/* Ficheros de imagen que si se deben encontrar */
	private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|jpg|mp4|png|tiff?))$");

	/* ruta en la que se guardaran las imagenes */
	private final File storageFolder;

	/*
	 * Contructor que inicializa el objeto que contiene las url, los dominios que se
	 * buscaran, los ficheros que se exluiran y los que se buscaran
	 */
	public ThReadCrawler(File storageFolder, CrawlerRequest crawlerRequest) {
		this.storageFolder = storageFolder;
		this.crawlerRequest = crawlerRequest;

		String filtro = ".*(\\.(";
		int contador = 1;
		String separador = "|";
		for (String extencion : crawlerRequest.getExtencionExclucion()) {

			/* Valida si es el elemento final de la lista */
			if (crawlerRequest.getExtencionExclucion().size() == contador) {
				separador = "))$";

			}
			filtro += extencion + separador;
			contador++;
		}

		this.FILTERS = Pattern.compile(filtro);

	}

	/*
	 * Este método recibe dos parámetros. El primer parámetro es la página en el que
	 * hemos descubierto esta nueva URL y el segundo parámetro es la nueva URL. Debe
	 * implementar esta función para especificar si la URL dada debe ser rastreada o
	 * no (según su lógica de rastreo). En este ejemplo, le indicamos al rastreador
	 * que ignore las URL que tener css, js, git, ... extensiones y solo aceptar URL
	 * que comienzan con "https://www.ics.uci.edu/". En este caso, no necesitamos el
	 * parámetro referencePage para tomar la decisión.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		if (FILTERS.matcher(href).matches()) {
			return false;
		}

		if (imgPatterns.matcher(href).matches()) {
			return true;
		}

		for (String domain : crawlerRequest.getDomainsFilter()) {
			if (href.startsWith(domain)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Esta función se llama cuando se recupera una página y está lista. para ser
	 * procesado por su programa.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println(url);
		System.out.println("tamaño imagen: " + page.getContentData().length);

		// Solo estamos interesados ​​en procesar imágenes
//		if (!imgPatterns.matcher(url).matches() || !((page.getParseData() instanceof BinaryParseData))) {
//			return;
//		}

		/*
		 * Si la extencion del archivo no es de tipo imagen o video o audio (de los
		 * formatos permitidos y definidos en imgPatterns), entonces retoran, es decir
		 * si es un html entonces retorna, y si no es de tipo imagen o audio o video
		 * tambien retorna
		 */
		if (!imgPatterns.matcher(url).matches() && !(page.getParseData() instanceof BinaryParseData)) {
			return;
		}

		/*
		 * En caso de que sea imagen, video, o audio entonces valida si el peso del
		 * archivo no sobrepasa lo permitido, para guardar
		 */
		if (page.getContentData().length > 100000000) {
			return;
		}

		// Obtenga un nombre único para almacenar esta imagen
		String extension = url.substring(url.lastIndexOf('.'));
		String hashedName = UUID.randomUUID() + extension;

		// Guarda la imagen
		String filename = storageFolder.getAbsolutePath() + '/' + hashedName;
		try {
			Files.write(page.getContentData(), new File(filename));
			WebCrawler.logger.info("Stored: {}", url);
		} catch (IOException iox) {
			WebCrawler.logger.error("Failed to write file: {}", filename, iox);
		}
	}

}
