package fr.ma.facturation.mock;

import java.util.ArrayList;
import java.util.List;

import fr.ma.facturation.modele.Facture;
import fr.ma.facturation.modele.FactureLigne;
import fr.ma.facturation.modele.Tva;
import fr.ma.facturation.utils.DateUtils;

public class MockFacture extends Facture {

	private static final long	serialVersionUID	= -5268458473230777252L;

	public MockFacture() {
		setVendeur(new MockVendeur());
		setAcheteur(new MockAcheteur());
		setNumero("FAC-2012-09");
		setUniteMonetaire("euro");
		setDateEmission(DateUtils.now());
		setMessageEntete("message entete");
		setMessageBasDePage("message bas de page");

		FactureLigne factureLigne = new FactureLigne();
		factureLigne.setReference("PRES1");
		factureLigne.setDescription("PRES DESCRIPTION");
		factureLigne.setPrixUnitaireHT(new Float(500));
		factureLigne.setQuantite(new Float(22));
		factureLigne.setTva(new Tva(1,20));
		factureLigne.setMontantHT(new Float(5000));

		List<FactureLigne>factureLignes = new ArrayList<FactureLigne>();
		factureLignes.add(factureLigne);
		setFactureLignes(factureLignes);

		// Totaux - doivent être calculé.
//		setTotalHT(new Float(10000));
//		setTotalTVA(new Float(2500));
//		setTotalTTC(new Float(12000));
	}

}
