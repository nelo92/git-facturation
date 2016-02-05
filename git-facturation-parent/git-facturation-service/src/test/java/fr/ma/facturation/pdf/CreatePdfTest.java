/*
 * Cree le 5 févr. 2016.
 * (c) Ag2r - La Mondiale, 2016. Tous droits reserves.
 */
package fr.ma.facturation.pdf;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ma.facturation.mock.MockFacture;

public class CreatePdfTest {

	private static final Logger LOG = LoggerFactory.getLogger(CreatePdfTest.class);

	@Test
	public void createFacture(){
		try {
			(new CreatePdf(new MockFacture())).create();
		} catch (Exception e) {
			LOG.error("createFacture Exception", e);
		}
	}

}
