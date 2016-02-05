package fr.ma.facturation.modele;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

public class Adresse implements Serializable {

	// Constantes -------------------------------------------------------------

	private static final long	serialVersionUID	= -5670468569276964936L;

	// Attributs --------------------------------------------------------------

	private String adresse1;
    private String adresse2;
	private String codePostal;
	private String ville;
	private String pays;

	// Getter/Setters ---------------------------------------------------------

	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}

	// Methods ----------------------------------------------------------------

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("adresse1", adresse1);
		sb.append("adresse2", adresse2);
		sb.append("codePostal", codePostal);
		sb.append("ville", ville);
		sb.append("pays", pays);
		return sb.toString();
	}
}
