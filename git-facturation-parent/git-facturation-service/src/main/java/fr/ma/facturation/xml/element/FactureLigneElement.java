package fr.ma.facturation.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

@XmlAccessorType(XmlAccessType.FIELD)
public class FactureLigneElement  {

	// Attributs --------------------------------------------------------------

	@XmlAttribute(name="reference", required=true)
	private String reference;
	@XmlAttribute(name="description", required=true)
	private String description;
	@XmlAttribute(name="unite", required=true)
	private String unite;
	@XmlAttribute(name="prixUnitaireHT", required=true)
	private Float prixUnitaireHT;
	@XmlAttribute(name="quantite", required=true)
	private Float quantite;
	@XmlElement(name = "tva", required = true)
	private TvaElement tva;

	// Getter/Setters ---------------------------------------------------------

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getPrixUnitaireHT() {
		return prixUnitaireHT;
	}
	public void setPrixUnitaireHT(Float prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}
	public Float getQuantite() {
		return quantite;
	}
	public void setQuantite(Float quantite) {
		this.quantite = quantite;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public TvaElement getTva() {
		return tva;
	}
	public void setTva(TvaElement tva) {
		this.tva = tva;
	}

	// Methods ----------------------------------------------------------------

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("reference", reference);
		sb.append("description", description);
		sb.append("prixUnitaireHT", prixUnitaireHT);
		sb.append("quantite", quantite);
		sb.append("unite", unite);
		sb.append("tva", tva);
		return sb.toString();
	}

}
