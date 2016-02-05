/*
 * Cree le 11 févr. 2016.
 * (c) Ag2r - La Mondiale, 2016. Tous droits reserves.
 */
package fr.ma.facturation.helper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ma.facturation.modele.Facture;
import fr.ma.facturation.modele.FactureLigne;
import fr.ma.facturation.modele.Tva;

public final class FactureHelper {

	// Constantes -------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(FactureHelper.class);

	// Constructeurs ----------------------------------------------------------

	private FactureHelper() { }

	// Methods / Validation ----------------------------------------------------------------

	private static boolean validate(FactureLigne fl){
		boolean v = true;
		if(fl==null){
			LOG.warn("FactureLigne is null !");
			v=false;
		}
		return v;
	}

	private static boolean validate(Facture f){
		boolean v = true;
		if (f != null) {
			List<FactureLigne> listfl = f.getFactureLignes();
			if (CollectionUtils.isEmpty(listfl)) {
				LOG.warn("Facture doesnt content LigneFacture !");
				v = false;
			}
		} else {
			LOG.warn("Facture is null !");
			v = false;
		}
		return v;
	}

	// Methods / Asssociate to LigneFacture ----------------------------------------------------------------

	public static Float calculMontantHt(FactureLigne fl) {
		Float c = 0f;
		LOG.debug("calculMontantHt for FactureLigne (ref={}) - begin...", fl.getReference());
		if(validate(fl)){
			Float u = fl.getPrixUnitaireHT();
			Float q = fl.getQuantite();
			if(u!=null && q!=null){
				c = u * q;
			}else{
				LOG.warn("Warning when calculate montantHt one of value is null (unite={}, quantite={}) !", u, q);
			}
		}
		LOG.debug("calculMontantHt - end.");
		return c;
	}

	public static Float calculMontantTtc(FactureLigne fl) {
		Float c = 0f;
		LOG.debug("calculMontantTtc for FactureLigne (ref={}) - begin...", fl.getReference());
		if(validate(fl)){
			Float mht = fl.getMontantHT();
			if(mht==null){
				mht = calculMontantHt(fl);
			}
			Tva tva = fl.getTva();
			if(mht!=null && tva!=null){
				Float tvaValue = tva.getValue();
				c = ((mht * tvaValue/100) + mht);
			}else{
				LOG.warn("Warning when calculate montantTtc one of value is null (montantHt={}, tva={}) !", mht, tva);
			}
		}
		LOG.debug("calculMontantTtc - end.");
		return c;
	}

	public static Float calculMontantTva(FactureLigne fl) {
		Float c = 0f;
		LOG.debug("calculMontantTva for FactureLigne (ref={}) - begin...", fl.getReference());
		if(validate(fl)){
			Float mht = fl.getMontantHT();
			if(mht==null){
				mht = calculMontantHt(fl);
			}
			Tva tva = fl.getTva();
			if(mht!=null && tva!=null){
				Float tvaValue = tva.getValue();
				c = (mht * tvaValue/100);
			}else{
				LOG.warn("Warning when calculate montantTva one of value is null (montantHt={}, tva={}) !", mht, tva);
			}
		}
		LOG.debug("calculMontantTva - end.");
		return c;
	}

	// Methods / Asssociate to Facture  ----------------------------------------------------------------

	public static Float calculTotalMontantHt(Facture f) {
		Float c = 0f;
		LOG.debug("calculTotalMontantHt for Facture (num={}) - begin...", f.getNumero());
		if (validate(f)) {
			for (FactureLigne fl : f.getFactureLignes()) {
				Float mht = fl.getMontantHT();
				if(mht!=null){
					c += mht;
				}else{
					LOG.warn("Warning FactureLigne (ref={}) content montantHT null !", fl.getReference());
				}
			}
		}
		LOG.debug("calculTotalMontantHt - end.");
		return c;
	}

	public static Float calculTotalMontantTtc(Facture f){
		Float c = 0f;
		LOG.debug("calculTotalMontantTtc for Facture (num={}) - begin...", f.getNumero());
		if (validate(f)) {
			for (FactureLigne fl : f.getFactureLignes()) {
				Float mttc = fl.getMontantTTC();
				if(mttc!=null){
					c += mttc;
				}else{
					LOG.warn("Warning FactureLigne (ref={}) content montantTTC null !", fl.getReference());
				}
			}
		}
		LOG.debug("calculTotalMontantTtc - end.");
		return c;
	}

	public static Float calculTotalTva(Facture f){
		Float c = 0f;
		LOG.debug("calculTotalTva for Facture (num={}) - begin...", f.getNumero());
		if (validate(f)) {
			for (FactureLigne fl : f.getFactureLignes()) {
				Float mtva = fl.getMontantTVA();
				if(mtva!=null){
					c += mtva;
				}else{
					LOG.warn("Warning FactureLigne (ref={}) content montantTVA null !", fl.getReference());
				}
			}
		}
		LOG.debug("calculTotalTva - end.");
		return c;
	}

	public static Float calculNetaPaye(Facture f){
		Float c = 0f;
		LOG.debug("calculNetaPaye for Facture (num={}) - begin...", f.getNumero());
		if (validate(f)) {
			Float mttc = f.getTotalTTC();
			Float mdp = f.getDejaPaye();
			if(mttc!=null && mdp!=null){
				c = mttc - mdp;
			}else{
				LOG.warn("Warning when calculate netaPaye one of value is null (totalTTC={}, dejaPaye={}) !", mttc, mdp);
			}
		}
		LOG.debug("calculNetaPaye - end.");
		return c;
	}

}
