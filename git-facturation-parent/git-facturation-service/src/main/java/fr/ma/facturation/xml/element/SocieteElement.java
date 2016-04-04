package fr.ma.facturation.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SocieteElement  {

	// Attributs --------------------------------------------------------------

	@XmlAttribute(name="nom", required=true)
	private String nom;
	@XmlAttribute(name="numeroIdentification", required=true)
	private String numeroIdentification;
	@XmlElement(name="adresse", required=true)
	private AdresseElement adresse;
	@XmlElement(name="contact", required=true)
	private ContactElement contact;

	// Methods ----------------------------------------------------------------

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public AdresseElement getAdresse() {
		return adresse;
	}
	public void setAdresse(AdresseElement adresse) {
		this.adresse = adresse;
	}

	public String getNumeroIdentification() {
		return numeroIdentification;
	}
	public void setNumeroIdentification(String numeroIdentification) {
		this.numeroIdentification = numeroIdentification;
	}
	public ContactElement getContact() {
		return contact;
	}
	public void setContact(ContactElement contact) {
		this.contact = contact;
	}

}
