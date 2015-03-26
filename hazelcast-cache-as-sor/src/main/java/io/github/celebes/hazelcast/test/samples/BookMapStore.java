package io.github.celebes.hazelcast.test.samples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.hazelcast.core.MapStore;

public class BookMapStore implements MapStore<Long, Book> {
	private EntityManager em;
	
	public BookMapStore() {
		System.out.println("BookMapStore constructor()");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
	    this.em = emf.createEntityManager();
	}

	@Override
	public Book load(Long key) {
		System.out.println("BookMapStore -> load()");
		
		Book result;
		
		try {
			result = em.createNamedQuery("findBookById", Book.class).setParameter("id", key).getSingleResult();
		} catch(NoResultException nre) {
			System.out.println("No result for id: " + key + " found");
			result = null;
		}
		
		return result;
	}

	@Override
	public Map<Long, Book> loadAll(Collection<Long> keys) {
		Map<Long, Book> result = new HashMap<Long, Book>();
        for (Long key : keys) result.put(key, load(key));
        return result;
	}

	@Override
	public Set<Long> loadAllKeys() {
		return null;
	}

	@Override
	public void store(Long key, Book value) {
		System.out.println("BookMapStore -> store()");
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(value);
	    tx.commit();
	}

	@Override
	public void storeAll(Map<Long, Book> map) {
		for (Map.Entry<Long, Book> entry : map.entrySet()) {
			store(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void delete(Long key) {
		EntityTransaction tx = em.getTransaction();
		Book book = load(key);
		tx.begin();
	    em.remove(book);
	    tx.commit();
	}

	@Override
	public void deleteAll(Collection<Long> keys) {
        for (Long key : keys) delete(key);
	}

}
