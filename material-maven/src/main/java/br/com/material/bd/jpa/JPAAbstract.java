package br.com.material.bd.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.material.bd.connection.JPAConnection;
import br.com.material.bd.interfaces.CrudDAO;

public class JPAAbstract<E, ID> extends JPAConnection implements CrudDAO<E, ID> {
	private Class<E> entity;
	
	@SuppressWarnings("unchecked")
	public JPAAbstract() {

		@SuppressWarnings("rawtypes")
		Class<? extends JPAAbstract> realClass = getClass();

		ParameterizedType superclass = (ParameterizedType) realClass
				.getGenericSuperclass();

		this.entity = (Class<E>) superclass.getActualTypeArguments()[0];
	}

	protected String getEntityName() {

		String nomePadrao = this.entity.getSimpleName();

		Entity annotation = this.entity.getAnnotation(Entity.class);

		if (annotation != null && !annotation.name().isEmpty()) {
			return annotation.name();
		}

		return nomePadrao;
	}
	public E getObject(ID id){

		EntityManager em = null;

		em = getEntityManager();
		
		E e = em.find(this.entity, id);

		this.closeConection();
		
		return e;
	}
	protected E getObject(String jpql){
		
		if(jpql == null || jpql.isEmpty()){
			jpql = "SELECT E FROM " + this.getEntityName() + " E";
		}

		EntityManager em = getEntityManager();

		TypedQuery<E> sql = em.createQuery(jpql, this.entity);
		
		E e = sql.getSingleResult();
		
		this.closeConection();
		
		return e;
	}
	public void add(E e){

		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		em.persist(e);

		em.getTransaction().commit();
		
		em.close();

		this.closeConection();
	}
	public void add(E e, EntityManager em){
		
		if(em != null && em.getTransaction().isActive()){
			em.persist(e);
		}
	}
	public void edit(E e){

		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		em.merge(e);

		em.getTransaction().commit();

		em.close();

		this.closeConection();
	}
	
	public void edit(E e, EntityManager em){
		
		if(em != null && em.getTransaction().isActive()){
			em.merge(e);
		}
	}

	public void remove(ID id){

		E e = this.getObject(id);

		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		e = em.merge(e);

		em.remove(e);

		em.getTransaction().commit();

		em.close();

		this.closeConection();
	}

	public List<E> list(){
		return this.list("");
	}

	protected List<E> list(String jpql){

		if(jpql == null || jpql.isEmpty()){
			jpql = "SELECT E FROM " + this.getEntityName() + " E";
		}
		
		EntityManager em = getEntityManager();

		TypedQuery<E> sql = em.createQuery(jpql, this.entity);

		List<E> listentity = sql.getResultList();

		this.closeConection();

		return listentity;
	}
}
