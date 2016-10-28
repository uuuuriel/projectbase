package br.com.material.service;

import javax.persistence.EntityManager;

import br.com.material.bd.interfaces.LogStockDAO;
import br.com.material.bd.interfaces.StockDAO;
import br.com.material.bd.jpa.DAOFactory;
import br.com.material.object.LogStock;
import br.com.material.object.Stock;

public class StockService extends ServiceAbstract<Stock,Integer,StockDAO>{

	@Override
	public void add(Stock e) throws Exception {
		
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		
		try{
			this.dao.add(e, em);
			
			LogStockDAO logDao = (LogStockDAO) DAOFactory.getInstanceOf(LogStockDAO.class);
			
			LogStock log = new LogStock();
			log.setStock(e);
			log.setOperacao(1);
			
			logDao.add(log, em);
			
			em.getTransaction().commit();
		}catch(Exception ex){
			em.getTransaction().rollback();
			throw ex;
		}finally{
			this.closeConection();
		}
	}
}
