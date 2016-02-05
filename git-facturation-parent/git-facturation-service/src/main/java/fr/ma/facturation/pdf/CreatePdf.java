package fr.ma.facturation.pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Cell;
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

@SuppressWarnings("unused")
public class CreatePdf {

	// Constantes -------------------------------------------------------------

	/** logger. */
	private static final Logger LOG = LoggerFactory.getLogger(CreatePdf.class);

	private static final Font FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0));

	private static final String PATH = "generated/FAC-TEST.pdf";

	//-------------------------------------------------------------------------
	// Tableau ligne de facture
	//-------------------------------------------------------------------------

	private static int TABLE_HEIGHT = 400;

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
		t.getDefaultCell().setBorder(Cell.NO_BORDER);
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
		t.addCell(printLigneFacturation(facture.getFactureLignes()));
		// Message bas de page
		t.addCell(printMessage(facture.getMessageBasDePage()));
		t.completeRow();
		d.add(t);
		LOG.debug("Document page height : {}",d.getPageSize().getHeight());
		d.close();
	}

	// Methods / Private ------------------------------------------------------

	private PdfPCell printMessage(String s) throws Exception {
		PdfPCell c = new PdfPCell(new Phrase(s, FONT));
		c.setBorder(PdfPCell.NO_BORDER);
		c.setColspan(2);
		return c;
	}

	private PdfPTable printContact(Contact contact) throws Exception {
		PdfPTable t = new PdfPTable(1);
		t.getDefaultCell().setBorder(Cell.NO_BORDER);
		t.addCell(createParagraph(contact.getNom() + " "+ contact.getPrenom()));
		t.addCell(createParagraph(contact.getMail()));
		t.addCell(createParagraph(contact.getTelephone()));
		return t;
	}

	private PdfPTable printAdresse(Adresse adresse) throws Exception {
		PdfPTable t = new PdfPTable(1);
		t.getDefaultCell().setBorder(Cell.NO_BORDER);
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
		t.getDefaultCell().setBorder(Cell.NO_BORDER);
		t.addCell(createParagraph(societe.getNom()));
		t.addCell(printAdresse(societe.getAdresse()));
		t.addCell(printContact(societe.getContact()));
		t.addCell(createParagraph(societe.getNumeroIdentification()));
		return c;
	}

	private PdfPCell printTitre() throws Exception {
		PdfPCell c ;
		PdfPTable t = new PdfPTable(1);
		c = new PdfPCell();
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
		PdfPCell c= new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.addElement(new Phrase("FACTURE EN "+ facture.getUniteMonetaire(), FONT));
		t.addCell(c);
		c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.addElement(new Phrase("N� "+ facture.getNumero(), FONT));
		t.addCell(c);
		c = new PdfPCell();
		c.setBorder(PdfPCell.NO_BORDER);
		c.addElement(new Phrase("Emise " + DateUtils.dateToString(facture.getDateEmission()), FONT));
		t.addCell(c);
		return t;
	}

	private PdfPCell printLigneFacturation(List<FactureLigne> flList){
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
		PdfPCell c = new PdfPCell(createPhrase(ENTETE_REFERENCE));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_DESIGNATION));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_QUANTITE));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_PRIX_UNITAIRE));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_MONTANT));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_TVA));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);
		t.addCell(c);
		return t;
	}

	private void printContentLigneFacturation(PdfPTable t, FactureLigne fl){
		t.addCell(new PdfPCell(createPhrase(fl.getReference())));
		t.addCell(new PdfPCell(createPhrase(fl.getDescription())));
		t.addCell(new PdfPCell(createPhrase(fl.getQuantite().toString())));
		t.addCell(new PdfPCell(createPhrase(fl.getPrixUnitaireHT().toString())));
		t.addCell(new PdfPCell(createPhrase(fl.getMontantHT().toString())));
		t.addCell(new PdfPCell(createPhrase(fl.getTva().toString())));
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
		ctva.addElement(printContentSumTva());
		ctva.setColspan(2);
		t.addCell(ctva);
		PdfPCell cCalcul = new PdfPCell();
		cCalcul.addElement(createPhrase("tab calcul"));
		cCalcul.setColspan(NB_COL_LIGNE_FACTURATION-2);
		t.addCell(cCalcul);
	}

	private PdfPTable printContentSumTva(){
		PdfPTable t = new PdfPTable(NB_COL_TOTAL_TVA);
		t.setWidthPercentage(90);
		PdfPCell c = new PdfPCell(createPhrase(ENTETE_REF_TVA));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_POURCENTAGE));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_MONTANT));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		c = new PdfPCell(createPhrase(ENTETE_TVA));
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c.setVerticalAlignment(Element.ALIGN_TOP);
		t.addCell(c);
		return t;
	}

	private PdfPTable printContentSumMontant(){
		PdfPTable t = new PdfPTable(2);
		return t;
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

//	private void traceCellHeight(PdfPCell c){
//		LOG.debug("Cell - max {} - min {}", c.getMaxHeight() , c.getMinimumHeight());
//		LOG.debug("Cell - fixedHeight {} - height {}", c.getFixedHeight() , c.getHeight());
//	}
//
//	LOG.debug("Table total height: {}", t.getTotalHeight());
//	LOG.debug("Table total width: {}", t.getTotalWidth());
//	for (int i = 0; i < nb; i++) {
//		PdfPRow r = t.getRow(i);
//		LOG.debug("Table rows[{}] calculateHeights : {}",i , r.calculateHeights());
//		LOG.debug("Table rows[{}] maxheight : {}", i, r.getMaxHeights());
////		PdfPCell[] cs = r.getCells();
////		for (int j = 0; j < cs.length; j++) {
////			traceCellHeight(cs[j]);
////		}
//	}

}
