/*
 * Cree le 17 févr. 2016.
 * (c) Ag2r - La Mondiale, 2016. Tous droits reserves.
 */
package fr.ma.facturation.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacturePropertiesService {

	// Constantes -------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(FacturePropertiesService.class);

	private static final String	FILE_NAME	= "config.properties";

	// Constructeurs ----------------------------------------------------------

	public FacturePropertiesService() { }

	// Methods ----------------------------------------------------------------

	public void getPropertyValues() throws IOException {
		Properties p = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		try {
			if (is != null) {
				p.load(is);
			} else {
				throw new FileNotFoundException("Property file " + FILE_NAME + " not found in the classpath");
			}
			// Test recuperation d'une propriete.
			String user = p.getProperty("user");
			LOG.debug("user = {}", user);
			// !Test recuperation d'une propriete.
		} catch (Exception e) {
			LOG.error("Exception :", e);
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new FacturePropertiesService().getPropertyValues();
	}

}
