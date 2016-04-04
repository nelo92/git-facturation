package fr.ma.facturation.xml.element;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "facture" )
public class FactureElement {

	// Attributs --------------------------------------------------------------
	@XmlAttribute(name="numero", required=true)
	private String				numero;
	@XmlAttribute(name="uniteMonetaire", required=true)
	private String				uniteMonetaire;
	@XmlAttribute(name="dateEmission", required=true)
	private Date				dateEmission;
	@XmlAttribute(name="messageEntete", required=true)
	private String				messageEntete;
	@XmlAttribute(name="messageBasDePage", required=true)
	private String				messageBasDePage;
	@XmlElement(name = "vendeur", required = true)
	private SocieteElement				vendeur;
	@XmlElement(name = "acheteur", required = true)
	private SocieteElement				acheteur;
	@XmlElementWrapper(name = "lignes")
	@XmlElement(name="ligne")
	private List<FactureLigneElement>	factureLignes;

	// Getter/Setters ---------------------------------------------------------

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public SocieteElement getVendeur() {
		return vendeur;
	}

	public void setVendeur(SocieteElement vendeur) {
		this.vendeur = vendeur;
	}

	public SocieteElement getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(SocieteElement acheteur) {
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

	public List<FactureLigneElement> getFactureLignes() {
		return factureLignes;
	}

	public void setFactureLignes(List<FactureLigneElement> factureLignes) {
		this.factureLignes = factureLignes;
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
		return sb.toString();
	}

}