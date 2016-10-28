package br.com.material.bd.jpa;

import br.com.material.bd.interfaces.StockDAO;
import br.com.material.object.Stock;

public class StockJPADAO extends JPAAbstract<Stock,Integer> implements StockDAO{}
