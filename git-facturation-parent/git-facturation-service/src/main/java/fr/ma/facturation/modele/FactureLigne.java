package fr.ma.facturation.modele;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.helper.FactureHelper;
import fr.ma.facturation.utils.ToStringStyle;

@SuppressWarnings("unused")
public class FactureLigne implements Serializable {

	// Constantes -------------------------------------------------------------

	private static final long	serialVersionUID	= 8658075801793894170L;

	// Attributs --------------------------------------------------------------

	private String reference;
	private String description;
	private String unite;
	private Float prixUnitaireHT;
	private Float quantite;
	private Tva tva;
	// Field Calculate
	private Float montantHT;
	private Float montantTTC;
	private Float montantTVA;

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
	public Tva getTva() {
		return tva;
	}
	public void setTva(Tva tva) {
		this.tva = tva;
	}
	public Float getMontantHT() {
		return (montantHT=FactureHelper.calculMontantHt(this));
	}
	public void setMontantHT(Float montantHT) {
		this.montantHT = montantHT;
	}
	public Float getMontantTTC() {
		return montantTTC;
	}
	public void setMontantTTC(Float montantTTC) {
		this.montantTTC = montantTTC;
	}
	public Float getMontantTVA() {
		return montantTVA;
	}
	public void setMontantTVA(Float montantTVA) {
		this.montantTVA = montantTVA;
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
		sb.append("montantHT", getMontantHT());
		sb.append("montantTTC", getMontantTTC());
		return sb.toString();
	}

}
