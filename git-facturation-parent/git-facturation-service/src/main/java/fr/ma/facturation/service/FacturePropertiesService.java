package fr.ma.facturation.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.ma.facturation.constant.PropertiesConstant;

public class FacturePropertiesService implements IFactureService {

	// Constantes -------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(FacturePropertiesService.class);


	// Constructeurs ----------------------------------------------------------

	public FacturePropertiesService() { }

	// Methods ----------------------------------------------------------------

	public void getPropertyValues() throws IOException {
		Properties p = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(PropertiesConstant.FILE_NAME);
		try {
			if (is != null) {
				p.load(is);
			} else {
				throw new FileNotFoundException("Property file " + PropertiesConstant.FILE_NAME + " not found in the classpath");
			}
			// Test recuperation d'une propriete.
			String mf = MessageFormat.format(PropertiesConstant.FACTURE_LIGNE_REFERENCE, 0);
			LOG.debug("MessageFormat result= {}", mf);
			LOG.debug("property result= {}", p.getProperty(mf));
			// !Test recuperation d'une propriete.
		} catch (Exception e) {
			LOG.error("Exception :", e);
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new FacturePropertiesService().getPropertyValues();
		// Chargement du context spring de la d'acces couche service.
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/services-context.xml");
	}

}
