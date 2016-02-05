package fr.ma.facturation.mock;

import fr.ma.facturation.modele.Adresse;
import fr.ma.facturation.modele.Contact;
import fr.ma.facturation.modele.Societe;

public class MockAcheteur extends Societe {

	private static final long	serialVersionUID	= -1757261791527437775L;

	public MockAcheteur() {
		setNom("SOC. Acheteur");
		Adresse adresse = new Adresse();
		adresse.setAdresse1("qqqq");
		adresse.setAdresse2("batiment 1");
		adresse.setCodePostal("75000");
		adresse.setVille("PARIS");
		adresse.setPays("FRANCE");
		setAdresse(adresse);
		Contact constact = new Contact();
		constact.setNom("AUGUTO");
		constact.setPrenom("Manuel");
		constact.setMail("xxx.xxxx@xxx.com");
		constact.setTelephone("06 00 00 00 00");
		setContact(constact);
		setNumeroIdentification("000000000000000000");
	}
}
