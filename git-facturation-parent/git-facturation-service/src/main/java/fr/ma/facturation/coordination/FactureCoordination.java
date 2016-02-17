package fr.ma.facturation.coordination;

import fr.ma.facturation.service.IFactureService;

public class FactureCoordination {

	private IFactureService factureService;

	public FactureCoordination() { }

	public IFactureService getFactureService() {
		return factureService;
	}

	public void setFactureService(IFactureService factureService) {
		this.factureService = factureService;
	}

}
