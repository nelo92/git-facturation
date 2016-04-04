package fr.ma.facturation.service;

import fr.ma.facturation.exception.ServiceException;


public interface IFactureService {

	public static final String FILE_NOT_EXIST_MSG_ERROR = "Error file does not exist : ";

	public void load() throws ServiceException;

}
