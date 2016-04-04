package fr.ma.facturation.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContactElement {

	// Attributs --------------------------------------------------------------

	@XmlAttribute(name="nom", required=true)
	private String nom;
	@XmlAttribute(name="prenom", required=true)
	private String prenom;
	@XmlAttribute(name="mail", required=true)
	private String mail;
	@XmlAttribute(name="telephone", required=true)
	private String telephone;

	// Getter/Setters ---------------------------------------------------------

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	// Methods ----------------------------------------------------------------

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("nom", nom);
		sb.append("prenom", prenom);
		sb.append("mail", mail);
		sb.append("telephone", telephone);
		return sb.toString();
	}
}
