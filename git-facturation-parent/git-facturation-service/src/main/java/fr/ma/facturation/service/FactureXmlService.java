package fr.ma.facturation.service;

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ma.facturation.constant.XmlConstant;
import fr.ma.facturation.exception.ServiceException;
import fr.ma.facturation.xml.element.FactureElement;


public class FactureXmlService implements IFactureService {

	// Constantes -------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(FactureXmlService.class);

	public static final String LOAD_MSG_ERROR = "Error when load Facture :";

	// Constructeurs ----------------------------------------------------------

	public FactureXmlService() { }

	// Methods ----------------------------------------------------------------

	public void load() throws ServiceException {
		LOG.debug("FactureXmlService - load...");
		loadxml();
		LOG.debug("FactureXmlService - load.");
	}

	public void loadxml() throws ServiceException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FactureElement.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = FactureXmlService.class.getClassLoader().getResource(XmlConstant.FILE_NAME);
			if(url!=null){
				File file = new File(url.getFile());
				FactureElement facture = (FactureElement) jaxbUnmarshaller.unmarshal(file);
				LOG.debug("Loaded facture = {}", facture);
			}else{
				throw new ServiceException(FILE_NOT_EXIST_MSG_ERROR + XmlConstant.FILE_NAME);
			}
		} catch (JAXBException e) {
			throw new ServiceException(LOAD_MSG_ERROR, e);
		}
	}

}
