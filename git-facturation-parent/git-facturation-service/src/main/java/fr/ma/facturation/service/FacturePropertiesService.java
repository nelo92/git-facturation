package fr.ma.facturation.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ma.facturation.constant.PropertiesConstant;
import fr.ma.facturation.exception.ServiceException;
import fr.ma.facturation.modele.Facture;
import fr.ma.facturation.modele.FactureLigne;
import fr.ma.facturation.modele.Societe;
import fr.ma.facturation.modele.Tva;
import fr.ma.facturation.utils.DateUtils;
import fr.ma.facturation.utils.StringUtils;

public class FacturePropertiesService implements IFactureService {

	// Constantes -------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(FacturePropertiesService.class);

	// Attributs --------------------------------------------------------------
	Properties properties = new Properties();

	// Constructeurs ----------------------------------------------------------

	public FacturePropertiesService() { }

	// Methods ----------------------------------------------------------------

	public void createFacture(){
		// Facture --
		Facture facture = new Facture();
		facture.setNumero(getProperty(PropertiesConstant.FACTURE_NUMERO));
		facture.setUniteMonetaire(getProperty(PropertiesConstant.FACTURE_UNITE_MONETAIRE));
		// TODO - Mettre la date du jour si pas de valeur récupéré depuis la propriété.
		facture.setDateEmission(DateUtils.now());
		facture.setMessageEntete(getProperty(PropertiesConstant.FACTURE_MESSAGE_ENTETE));
		facture.setMessageBasDePage(getProperty(PropertiesConstant.FACTURE_MESSAGE_BASDEPAGE));
		// Vendeur --
		Societe vendeur = new Societe();
		facture.setVendeur(vendeur);
		// Acheteur --
		Societe acheteur = new Societe();
		facture.setAcheteur(acheteur);
		// Ligne de facture  --
		List<FactureLigne>factureLignes = new ArrayList<FactureLigne>();
		// 1 - Recuperation du nb ligne
		String sNbLigne = getProperty(PropertiesConstant.FACTURE_NB_LIGNE);
		if(StringUtils.isNotBlank(sNbLigne)){
			int nbLigne = Integer.parseInt(sNbLigne);
			for(int i=0; i<nbLigne; i++){
				FactureLigne factureLigne = new FactureLigne();
				factureLigne.setReference(getProperty(PropertiesConstant.FACTURE_LIGNE_REFERENCE,i));
				factureLigne.setDescription(getProperty(PropertiesConstant.FACTURE_LIGNE_DESCRIPTION,i));
				factureLigne.setPrixUnitaireHT(getPropertyFloat(PropertiesConstant.FACTURE_LIGNE_PRIX_UNITAIRE_HT,i));
				factureLigne.setQuantite(getPropertyFloat(PropertiesConstant.FACTURE_QUANTITE,i));
				// Tva
				Integer tvaRef = getPropertyInteger(PropertiesConstant.FACTURE_TVA_REF,i);
				Float tvaValue = getPropertyFloat(PropertiesConstant.FACTURE_TVA_VALUE,i);
				Tva tva = new Tva(tvaRef,tvaValue);
				factureLigne.setTva(tva);
				// Add to list
				factureLignes.add(factureLigne);
			}
		}
	}

	public void loadProperties(){
		InputStream is = getClass().getClassLoader().getResourceAsStream(PropertiesConstant.FILE_NAME);
		try {
			if (is != null) {
				properties.load(is);
			} else {
				throw new FileNotFoundException("Property file " + PropertiesConstant.FILE_NAME + " not found in the classpath");
			}
			createFacture();
		} catch (FileNotFoundException e) {
			LOG.error("loadProperties FileNotFoundException", e);
		} catch (IOException e) {
			LOG.error("loadProperties IOException", e);
		}
	}

	public void load() throws ServiceException {
		LOG.debug("FacturePropertiesService - load...");
		loadProperties();
		LOG.debug("FacturePropertiesService - load.");
	}

	// Methods / private -----------------------------------------------------

	private Float getPropertyFloat(String property, int index){
		Float value = null;
		String pValue = getProperty(property,index);
		if(StringUtils.isNotEmpty(pValue)){
			value = Float.valueOf(pValue);
		}
		return value;
	}

	private Integer getPropertyInteger(String property, int index){
		Integer value =null;
		String pValue = getProperty(property,index);
		if(StringUtils.isNotEmpty(pValue)){
			value = Integer.valueOf(pValue);
		}
		return value;
	}

	private String getProperty(String property){
		return properties.getProperty(property);
	}

	private String getProperty(String property, int index){
		return properties.getProperty(MessageFormat.format(property, index));
	}

}
