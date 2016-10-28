package br.com.material.service;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.material.bd.connection.JPAConnection;
import br.com.material.bd.interfaces.CrudDAO;
import br.com.material.bd.jpa.DAOFactory;
import br.com.material.exception.MainException;
import br.com.material.exception.ValException;

public class ServiceAbstract<E, ID, DAO extends CrudDAO<E, ID>> extends JPAConnection implements
		ServiceBase<E, ID> {

	protected DAO dao;

	@SuppressWarnings("unchecked")
	public ServiceAbstract() {

		@SuppressWarnings("rawtypes")
		Class<? extends ServiceAbstract> realClass = getClass();

		ParameterizedType superclass = (ParameterizedType) realClass
				.getGenericSuperclass();

		Class<DAO> dao = (Class<DAO>) superclass.getActualTypeArguments()[2];

		this.dao = (DAO) DAOFactory.getInstanceOf(dao);
	}

	private void valEntity(E e) throws MainException {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<E>> constraintViolations = validator
				.validate(e);
		if (constraintViolations.size() > 0) {
			Iterator<ConstraintViolation<E>> iterator = constraintViolations
					.iterator();

			String msg = "";

			while (iterator.hasNext()) {
				ConstraintViolation<E> cv = iterator.next();

				msg += "- " + cv.getMessage();
			}

			throw new ValException(msg);
		}
	}

	public E getObject(ID id) throws MainException {
		return this.dao.getObject(id);
	}

	public void add(E e) throws Exception {

		this.valEntity(e);

		this.dao.add(e);
	}

	public void edit(E e) throws Exception {

		this.valEntity(e);

		this.dao.edit(e);
	}

	public void remove(ID id) throws Exception {
		this.dao.remove(id);
	}

	public List<E> list() throws MainException {
		return this.dao.list();
	}
}
