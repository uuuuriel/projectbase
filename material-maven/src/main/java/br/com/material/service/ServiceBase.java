package br.com.material.service;

import java.util.List;

public interface ServiceBase<E, ID> {

	public E getObject(ID id) throws Exception;

	public void add(E e) throws Exception;

	public void edit(E e) throws Exception;

	public void remove(ID id) throws Exception;

	public List<E> list() throws Exception;
}
