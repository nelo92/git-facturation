package fr.ma.facturation.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.helper.FactureHelper;
import fr.ma.facturation.utils.ToStringStyle;

@SuppressWarnings("unused")
public class Facture implements Serializable {

	// Constantes -------------------------------------------------------------

	private static final long	serialVersionUID	= -7072304184660950548L;

	// Attributs --------------------------------------------------------------

	private String				numero;
	private Societe				vendeur;
	private Societe				acheteur;
	private String				uniteMonetaire;
	private Date				dateEmission;
	private String				messageEntete;
	private String				messageBasDePage;
	private List<FactureLigne>	factureLignes;
	// Fields calculate
	private Float				totalTVA;
	private Float				totalHT;
	private Float				totalTTC;

	// Getter/Setters ---------------------------------------------------------

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Societe getVendeur() {
		return vendeur;
	}

	public void setVendeur(Societe vendeur) {
		this.vendeur = vendeur;
	}

	public Societe getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Societe acheteur) {
		this.acheteur = acheteur;
	}

	public String getUniteMonetaire() {
		return uniteMonetaire;
	}

	public void setUniteMonetaire(String uniteMonetaire) {
		this.uniteMonetaire = uniteMonetaire;
	}

	public Date getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Date dateEmission) {
		this.dateEmission = dateEmission;
	}

	public String getMessageEntete() {
		return messageEntete;
	}

	public void setMessageEntete(String messageEntete) {
		this.messageEntete = messageEntete;
	}

	public String getMessageBasDePage() {
		return messageBasDePage;
	}

	public void setMessageBasDePage(String messageBasDePage) {
		this.messageBasDePage = messageBasDePage;
	}

	public List<FactureLigne> getFactureLignes() {
		return factureLignes;
	}

	public void setFactureLignes(List<FactureLigne> factureLignes) {
		this.factureLignes = factureLignes;
	}

	public Float getTotalTVA() {
		return (totalTVA=(FactureHelper.calculTotalTva(this)));
	}

	public void setTotalTVA(Float totalTVA) {
		this.totalTVA = totalTVA;
	}

	public Float getTotalHT() {
		return (totalHT=FactureHelper.calculTotalMontantHt(this));
	}

	public void setTotalHT(Float totalHT) {
		this.totalHT = totalHT;
	}

	public Float getTotalTTC() {
		return (totalTTC=FactureHelper.calculTotalMontantTtc(this));
	}

	public void setTotalTTC(Float totalTTC) {
		this.totalTTC = totalTTC;
	}

	// Methods ----------------------------------------------------------------

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("numero", numero);
		sb.append("vendeur", vendeur);
		sb.append("acheteur", acheteur);
		sb.append("uniteMonetaire", uniteMonetaire);
		sb.append("dateEmission", dateEmission);
		sb.append("messageEntete", messageEntete);
		sb.append("messageBasDePage", messageBasDePage);
		sb.append("FactureLignes", factureLignes);
		sb.append("totalHT", getTotalHT());
		sb.append("totalTVA", getTotalTVA());
		sb.append("totalTTC", getTotalTTC());
		return sb.toString();
	}

}