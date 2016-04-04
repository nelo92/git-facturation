package fr.ma.facturation.constant;

public final class PropertiesConstant {

	// Constructeurs ----------------------------------------------------------

	private PropertiesConstant() { }

	// Attributs --------------------------------------------------------------

	public static final String	FILE_NAME	= "data/facture.properties";

	public static final String SEP = ".";

	// ------------------------------------------------------------------------
	// Adresse
	// ------------------------------------------------------------------------

	public static final String ADRESSE_LIGNE1 = "adresse.ligne1";
	public static final String ADRESSE_LIGNE2 = "adresse.ligne2";
	public static final String ADRESSE_CODE_POSTAL = "adresse.codepostal";
	public static final String ADRESSE_VILLE = "adresse.ville";
	public static final String ADRESSE_PAYS = "adresse.pays";

	// ------------------------------------------------------------------------
	// Contact
	// ------------------------------------------------------------------------

	public static final String CONTACT_NOM = "contact.nom";
	public static final String CONTACT_PRENOM = "contact.prenom";
	public static final String CONTACT_MAIL = "contact.mail";
	public static final String CONTACT_TELEPHONE = "contact.telephone";

	// ------------------------------------------------------------------------
	// Societe
	// ------------------------------------------------------------------------

	public static final String SOCIETE_NOM = "nom";
	public static final String SOCIETE_NUM_IDENTIFICATION = "numIdentification";

	// ------------------------------------------------------------------------
	// Vendeur
	// ------------------------------------------------------------------------

	public static final String VENDEUR = "vendeur";
	public static final String VENDEUR_SOCIETE_NOM = VENDEUR + SEP + SOCIETE_NOM;
	public static final String VENDEUR_ADRESSE_LIGNE1 = VENDEUR + SEP + SOCIETE_NUM_IDENTIFICATION;
	public static final String VENDEUR_ADRESSE_LIGNE2 = VENDEUR + SEP + SOCIETE_NOM;
	public static final String VENDEUR_ADRESSE_CODE_POSTAL = VENDEUR + SEP + SOCIETE_NUM_IDENTIFICATION;
	public static final String VENDEUR_ADRESSE_VILLE = VENDEUR + SEP + ADRESSE_VILLE;
	public static final String VENDEUR_ADRESSE_PAYS = VENDEUR + SEP + ADRESSE_PAYS ;

	// ------------------------------------------------------------------------
	// Acheteur
	// ------------------------------------------------------------------------

	public static final String ACHETEUR = "acheteur";
	public static final String ACHETEUR_SOCIETE_NOM = ACHETEUR + SEP + SOCIETE_NOM;
	public static final String ACHETEUR_ADRESSE_LIGNE1 = ACHETEUR + SEP + SOCIETE_NUM_IDENTIFICATION;
	public static final String ACHETEUR_ADRESSE_LIGNE2 = ACHETEUR + SEP + SOCIETE_NOM;
	public static final String ACHETEUR_ADRESSE_CODE_POSTAL = ACHETEUR + SEP + SOCIETE_NUM_IDENTIFICATION;
	public static final String ACHETEUR_ADRESSE_VILLE = ACHETEUR + SEP + ADRESSE_VILLE;
	public static final String ACHETEUR_ADRESSE_PAYS = ACHETEUR + SEP + ADRESSE_PAYS ;

	// ------------------------------------------------------------------------
	// Facture
	// ------------------------------------------------------------------------

	public static final String FACTURE_NUMERO = "facture.numero";
	public static final String FACTURE_UNITE_MONETAIRE = "facture.uniteMonetaire";
	public static final String FACTURE_DATE_EMISSION = "facture.dateEmission";
	public static final String FACTURE_MESSAGE_ENTETE = "facture.messageEntete";
	public static final String FACTURE_MESSAGE_BASDEPAGE = "facture.messageBasDePage";
	public static final String FACTURE_NB_LIGNE = "facture.nbligne";

	// ------------------------------------------------------------------------
	// Ligne de Facture
	// ------------------------------------------------------------------------

//	public static final String FACTURE_LIGNE_INDEX = "facture.ligne";

	public static final String FACTURE_LIGNE_REFERENCE = "facture.ligne{0}.reference";
	public static final String FACTURE_LIGNE_DESCRIPTION = "facture.ligne{0}.description";
	public static final String FACTURE_LIGNE_UNITE = "facture.ligne{0}.unite";
	public static final String FACTURE_LIGNE_PRIX_UNITAIRE_HT= "facture.ligne{0}.prixUnitaireHT";
	public static final String FACTURE_QUANTITE = "facture.ligne{0}.quantite";
	public static final String FACTURE_TVA_REF = "facture.ligne{0}.tva.ref";
	public static final String FACTURE_TVA_VALUE = "facture.ligne{0}.tva.value";

}
