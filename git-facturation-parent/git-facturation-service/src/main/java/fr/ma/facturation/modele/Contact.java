package fr.ma.facturation.modele;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

public class Contact implements Serializable {

	// Constantes -------------------------------------------------------------

	private static final long	serialVersionUID	= -7285550534993301078L;

	// Attributs --------------------------------------------------------------

	private String nom;
	private String prenom;
	private String mail;
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
