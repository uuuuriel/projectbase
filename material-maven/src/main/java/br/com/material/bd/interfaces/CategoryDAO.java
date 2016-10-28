package br.com.material.bd.interfaces;

import br.com.material.object.Category;

public interface CategoryDAO extends CrudDAO<Category,Integer>{
	
	public int validaCategoria(Category e);
}
