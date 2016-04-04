package fr.ma.facturation.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

@XmlAccessorType(XmlAccessType.FIELD)
public class  AdresseElement {

	// Attributs --------------------------------------------------------------

	@XmlAttribute(name="adresse1", required=true)
	private String adresse1;
	@XmlAttribute(name="adresse2", required=true)
    private String adresse2;
	@XmlAttribute(name="codePostal", required=true)
	private String codePostal;
	@XmlAttribute(name="ville", required=true)
	private String ville;
	@XmlAttribute(name="pays", required=true)
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
