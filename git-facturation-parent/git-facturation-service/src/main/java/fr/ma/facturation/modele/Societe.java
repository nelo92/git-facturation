package fr.ma.facturation.modele;

import java.io.Serializable;

public class Societe implements Serializable {

	// Attributs --------------------------------------------------------------

	private String nom;
	private Adresse adresse;
	private Contact contact;
	private String numeroIdentification;

	// Methods ----------------------------------------------------------------

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getNumeroIdentification() {
		return numeroIdentification;
	}
	public void setNumeroIdentification(String numeroIdentification) {
		this.numeroIdentification = numeroIdentification;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
