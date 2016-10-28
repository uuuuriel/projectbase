package br.com.material.bd.jpa;

import br.com.material.bd.interfaces.CategoryDAO;
import br.com.material.bd.interfaces.LogStockDAO;
import br.com.material.bd.interfaces.StockDAO;

public class DAOFactory {

	@SuppressWarnings("rawtypes")
	public static Object getInstanceOf(Class c){
		
		if(c.equals(CategoryDAO.class)){
			return new CategoryJPADAO();
		}else if(c.equals(StockDAO.class)){
			return new StockJPADAO();
		}else if(c.equals(LogStockDAO.class)){
			return new LogStockJPADAO();
		}
		
		return null;
	}
}
