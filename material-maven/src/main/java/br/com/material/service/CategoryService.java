package br.com.material.service;

import br.com.material.bd.interfaces.CategoryDAO;
import br.com.material.exception.CategoryExceptionVal;
import br.com.material.object.Category;
public class CategoryService extends
		ServiceAbstract<Category, Integer, CategoryDAO> {
	@Override
	public void add(Category categoria) throws Exception {

		int numeroCategorias = this.dao.validaCategoria(categoria);

		if (numeroCategorias > 0) {
			throw new CategoryExceptionVal();
		}

		super.add(categoria);
	}

	@Override
	public void edit(Category categoria) throws Exception {

		int numeroCategorias = this.dao.validaCategoria(categoria);

		if (numeroCategorias > 0) {
			throw new CategoryExceptionVal();
		}

		super.edit(categoria);
	}
}
