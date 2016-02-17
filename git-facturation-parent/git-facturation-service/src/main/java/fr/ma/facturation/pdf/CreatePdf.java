package fr.ma.facturation.pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import fr.ma.facturation.modele.Adresse;
import fr.ma.facturation.modele.Contact;
import fr.ma.facturation.modele.Facture;
import fr.ma.facturation.modele.FactureLigne;
import fr.ma.facturation.modele.Societe;
import fr.ma.facturation.utils.DateUtils;

public class CreatePdf {

	// Constantes -------------------------------------------------------------

	/** logger. */
	private static final Logger LOG = LoggerFactory.getLogger(CreatePdf.class);

	private static final String PATH = "generated/FAC-TEST.pdf";

	private static final Font FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0));

	//-------------------------------------------------------------------------
	// Tableau ligne de facture
	//-------------------------------------------------------------------------

	private static int TABLE_HEIGHT = 350;

	private static final String ENTETE_REFERENCE = "REF";
	private static final String ENTETE_DESIGNATION = "DESCRIPTION";
	private static final String ENTETE_QUANTITE = "QUANTITE";
	private static final String ENTETE_PRIX_UNITAIRE = "PRIX UNITAIRE";
	private static final String ENTETE_MONTANT = "MONTANT";
	private static final String ENTETE_TVA = "TVA";


	private static final String[] ENTETE_LIGNE_FACTURATION	= { ENTETE_REFERENCE, ENTETE_DESIGNATION,
			ENTETE_QUANTITE, ENTETE_PRIX_UNITAIRE, ENTETE_MONTANT, ENTETE_TVA };

	private static int NB_COL_LIGNE_FACTURATION = ENTETE_LIGNE_FACTURATION.length;

	//-------------------------------------------------------------------------
	// Tableau somme tva
	//-------------------------------------------------------------------------

	private static final String ENTETE_REF_TVA = "";
	private static final String ENTETE_POURCENTAGE = "%";

	private static final String[] ENTETE_TOTAL_TVA = { ENTETE_REF_TVA, ENTETE_POURCENTAGE, ENTETE_MONTANT, ENTETE_TVA };

	private static int NB_COL_TOTAL_TVA = ENTETE_TOTAL_TVA.length;

	//-------------------------------------------------------------------------
	// Tableau somme
	//-------------------------------------------------------------------------

	private static final String TOTAL_HT = "TOTAL HT";
	private static final String TOTAL_TVA = "TOTAL TVA";
	private static final String TOTAL_TTC = "TOTAL TTC";
	private static final String TOTAL_DEJA_PAYE = "DEJA PAYE";
	private static final String TOTAL_NET_PAYE = "NET A PAYE";


	// Attributs --------------------------------------------------------------

	private Facture facture = null;

	// Constructeurs ----------------------------------------------------------

	public CreatePdf(Facture facture) {
		this.facture = facture;
	}

	// Methods ----------------------------------------------------------------

	public void create()  throws Exception {
		Document d = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter.getInstance(d, new FileOutputStream(PATH));
		d.open();
		PdfPTable t = new PdfPTable(2);
		t.setWidthPercentage(100);
		t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		// Vendeur
		t.addCell(printSociete(facture.getVendeur()));
		// Titre
		t.addCell(printTitre());
		// Cellule vide
		t.addCell(createParagraph(StringUtils.EMPTY));
		// Acheteur
		t.addCell(printSociete(facture.getAcheteur()));
		// Message Entete
		t.addCell(printMessage(facture.getMessageEntete()));
		// Ligne de facture
		t.addCell(printLigneFacturation());
		// Message bas de page
		t.addCell(printMessage(facture.getMessageBasDePage()));
		t.completeRow();
		d.add(t);
		LOG.debug("Document page height : {}",d.getPageSize().getHeight());
		d.close();
	}

	// Methods / Private ------------------------------------------------------

	private PdfPCell printMessage(String s) throws Exception {
		PdfPCell c = createCell(s);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setColspan(2);
		return c;
	}

	private PdfPTable printContact(Contact contact) throws Exception {
		PdfPTable t = new PdfPTable(1);
		t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		t.addCell(createParagraph(contact.getNom() + " "+ contact.getPrenom()));
		t.addCell(createParagraph(contact.getMail()));
		t.addCell(createParagraph(contact.getTelephone()));
		return t;
	}

	private PdfPTable printAdresse(Adresse adresse) throws Exception {
		PdfPTable t = new PdfPTable(1);
		t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		t.addCell(createParagraph(adresse.getAdresse1()));
		t.addCell(createParagraph(adresse.getAdresse2()));
		t.addCell(createParagraph(adresse.getCodePostal() + " " + adresse.getVille()));
		return t;
	}

	private PdfPCell printSociete(final Societe societe) throws Exception  {
		PdfPTable t = new PdfPTable(1);
		PdfPCell c = new PdfPCell(t);
		c.setBorder(PdfPCell.NO_BORDER);
		t.setTotalWidth(100f);
		t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		t.addCell(createParagraph(societe.getNom()));
		t.addCell(printAdresse(societe.getAdresse()));
		t.addCell(printContact(societe.getContact()));
		t.addCell(createParagraph(societe.getNumeroIdentification()));
		return c;
	}

	private PdfPCell printTitre() throws Exception {
		PdfPTable t = new PdfPTable(1);
		PdfPCell c = new PdfPCell();
		c.setCellEvent(new RoundRectangleCellEvent());
		c.setBorder(PdfPCell.NO_BORDER);
		c.addElement(printTitreContent());
		t.addCell(c);
		c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.addElement(t);
		return c;
	}

	private PdfPTable printTitreContent() {
		PdfPTable t = new PdfPTable(1);
		PdfPCell c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		c.addElement(createPhrase("FACTURE EN "+ facture.getUniteMonetaire()));
		t.addCell(c);
		c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		c.addElement(createPhrase("N� "+ facture.getNumero()));
		t.addCell(c);
		c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		c.addElement(createPhrase("Emise " + DateUtils.dateToString(facture.getDateEmission())));
		t.addCell(c);
		return t;
	}

	private PdfPCell printLigneFacturation(){
		List<FactureLigne> flList = facture.getFactureLignes();
		PdfPCell c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.setColspan(2);
		PdfPTable t = printEneteLigneFacturation();
		if(CollectionUtils.isNotEmpty(flList)){
			for (FactureLigne fl : flList) {
				printContentLigneFacturation(t, fl);
			}
		}
		printContentLigneFacturationEmpty(t);
		printContentLigneFacturationEnd(t);
		t.completeRow();
		c.addElement(t);
		return c;
	}

	private PdfPTable printEneteLigneFacturation(){
		PdfPTable t = new PdfPTable(NB_COL_LIGNE_FACTURATION);
		t.setWidthPercentage(100);
		PdfPCell c = createCell(ENTETE_REFERENCE);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = createCell(ENTETE_DESIGNATION);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = createCell(ENTETE_QUANTITE);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = createCell(ENTETE_PRIX_UNITAIRE);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = createCell(ENTETE_MONTANT);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = createCell(ENTETE_TVA);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		return t;
	}

	private void printContentLigneFacturation(PdfPTable t, FactureLigne fl){
		t.addCell(createCell(fl.getReference()));
		t.addCell(createCell(fl.getDescription()));
		t.addCell(createCell(printValue(fl.getQuantite())));
		t.addCell(createCell(printValue(fl.getPrixUnitaireHT())));
		t.addCell(createCell(printValue(fl.getMontantHT())));
		Integer tvaRef = (fl.getTva()!=null)?fl.getTva().getRef() : null;
		t.addCell(createCell(printValue(tvaRef)));
	}

	private void printContentLigneFacturationEmpty(PdfPTable t){
		// Calcule de la hauteur du tableau.
		int h = 0;
		int nb = CollectionUtils.isNotEmpty(t.getRows()) ? t.getRows().size() : 0;
		for (int i = 0; i < nb; i++) {
			h +=  t.getRow(i).getMaxHeights();
		}
		// Creation de la cellule remplissant la hauteur du tableau attendu.
		if (h < TABLE_HEIGHT) {
			h = (TABLE_HEIGHT - h);
			for (int i = 0; i < NB_COL_LIGNE_FACTURATION; i++) {
				PdfPCell c = new PdfPCell();
				c.setFixedHeight(h);
				t.addCell(c);
			}
		}
	}

	private void printContentLigneFacturationEnd(PdfPTable t){
		PdfPCell ctva = new PdfPCell();
		ctva.setBorder(PdfPCell.NO_BORDER);
		ctva.addElement(printContentSumTva());
		ctva.setColspan(2);
		t.addCell(ctva);
		PdfPCell cCalcul = new PdfPCell();
		cCalcul.setBorder(PdfPCell.NO_BORDER);
		cCalcul.addElement(printContentSumMontant());
		cCalcul.setColspan(NB_COL_LIGNE_FACTURATION-2);
		t.addCell(cCalcul);
	}

	private PdfPTable printContentSumTva(){
		PdfPTable t = new PdfPTable(NB_COL_TOTAL_TVA);
		t.setWidthPercentage(90);
		// Entete
		PdfPCell c = createCell(ENTETE_REF_TVA);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = createCell(ENTETE_POURCENTAGE);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = createCell(ENTETE_MONTANT);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = createCell(ENTETE_TVA);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		// Contenu
		t.addCell(createCell(""));
		t.addCell(createCell(""));
		t.addCell(createCell(""));
		t.addCell(createCell(""));
		return t;
	}

	private PdfPTable printContentSumMontant(){
		PdfPTable t = new PdfPTable(4);
		t.setWidthPercentage(100);
		PdfPCell c = createCell(TOTAL_HT);
		c.setColspan(2);
		t.addCell(c);
		t.addCell(printValue(facture.getTotalHT()));
		// Colonne vide
		c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.setRowspan(5);
		t.addCell(c);
		c = createCell(TOTAL_TVA);
		c.setColspan(2);
		t.addCell(c);
		t.addCell(printValue(facture.getTotalTVA()));
		c = createCell(TOTAL_TTC);
		c.setColspan(2);
		t.addCell(c);
		t.addCell(printValue(facture.getTotalTTC()));
		c = createCell(TOTAL_DEJA_PAYE);
		c.setColspan(2);
		t.addCell(c);
		t.addCell(printValue(facture.getDejaPaye()));
		c = createCell(TOTAL_NET_PAYE);
		c.setColspan(2);
		t.addCell(c);
		t.addCell(printValue(facture.getNetaPaye()));
		return t;
	}

	private String printValue(Integer i){
		return (i!=null) ? i.toString() : StringUtils.EMPTY;
	}

	private String printValue(Float f){
		return (f!=null) ? String.format("%.2f", f) : StringUtils.EMPTY;
	}

	private PdfPCell createCell(String s){
		return new PdfPCell(createPhrase(s));
	}

	private Paragraph createParagraph(String s) {
		return new Paragraph(s, FONT);
	}

	private Phrase createPhrase(String s){
		return new Phrase(s, FONT);
	}

}
