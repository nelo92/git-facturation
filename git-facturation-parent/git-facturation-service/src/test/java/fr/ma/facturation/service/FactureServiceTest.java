/*
 * Cree le 4 avr. 2016.
 * (c) Ag2r - La Mondiale, 2016. Tous droits reserves.
 */
package fr.ma.facturation.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.ma.facturation.exception.ServiceException;

public class FactureServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(FactureServiceTest.class);

	ApplicationContext ctx = null;

	@Before
	public void initialize() {
		LOG.debug("Begin test...");
		ctx = new ClassPathXmlApplicationContext("spring/services-context.xml");
	}

	public void ending(){
		LOG.debug("End test.");
	}

	@Test
	public void loadProperties(){
//		ctx = new ClassPathXmlApplicationContext("spring/services-context.xml");
		IFactureService factureService = (IFactureService)ctx.getBean("facturePropertiesService");
		try {
			factureService.load();
		} catch (ServiceException e) {
			LOG.error("load ServiceException", e);
		}
	}

	@Test
	public void loadXml(){
		IFactureService factureService = (IFactureService)ctx.getBean("factureXmlService");
		try {
			factureService.load();
		} catch (ServiceException e) {
			LOG.error("load ServiceException", e);
		}
	}

}
