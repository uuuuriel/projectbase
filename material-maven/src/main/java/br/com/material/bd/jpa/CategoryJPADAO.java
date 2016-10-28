package br.com.material.bd.jpa;

import br.com.material.bd.interfaces.CategoryDAO;
import br.com.material.object.Category;

public class CategoryJPADAO extends JPAAbstract<Category, Integer> implements
		CategoryDAO {
	public int validaCategoria(Category e) {

		String jpql = "SELECT C FROM " + this.getEntityName()
				+ " C WHERE C.name = '" + e.getName() + "'";
		if (e.getId() > 0) {
			jpql += " AND C.id <> " + e.getId();
		}

		return this.list(jpql).size();
	}
}
