package io.github.celebes.hazelcast.test.samples.dao.impl;

import io.github.celebes.hazelcast.test.samples.EntityObject;
import io.github.celebes.hazelcast.test.samples.dao.GenericDao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*
 * Zajmuje sie zapisem do bazy danych
 */

public abstract class GenericJpaDaoImpl<T extends EntityObject> extends GenericDaoImpl<T> implements GenericDao<T> {
	
	private EntityManager em;
	
	public GenericJpaDaoImpl() {
		this.em = Persistence.createEntityManagerFactory("main-db").createEntityManager();
		System.out.println("em = " + em);
	}

	@Override
	public void save(T t) {
		System.out.println("JPA DAO - SAVE");
		System.out.println("em = " + em);
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(t);
	    tx.commit();
	}

	@Override
	public void delete(T t) {
		System.out.println("JPA DAO - DELETE");
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.remove(em.contains(t) ? t : em.merge(t));
		tx.commit();
	}

	@Override
	public void update(T t) {
		System.out.println("JPA DAO - UPDATE");
		save(t);
	}

	@Override
	public T findById(Long id) {
		System.out.println("JPA DAO - FIND BY ID");
		return em.find(type, id);
	}

	@Override
	public Collection<T> findAll() {
		System.out.println("JPA DAO - FIND ALL");
		return em.createQuery("SELECT e FROM " + type.getSimpleName() + " e").getResultList();
	}

}
