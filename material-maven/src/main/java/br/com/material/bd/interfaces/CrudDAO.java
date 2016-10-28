package br.com.material.bd.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

public interface CrudDAO<E, ID> {
	public E getObject(ID id);
	public void add(E e);
	public void add(E e, EntityManager em);

	public void edit(E e);
	public void edit(E e, EntityManager em);

	public void remove(ID id);

	public List<E> list();
}
