package io.github.celebes.hazelcast.test.samples;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class BookDaoImpl implements BookDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	private IMap<Long, Book> map;

	public BookDaoImpl(IMap<Long, Book> map) {
		this.map = map;
		emf = Persistence.createEntityManagerFactory("chapter04PU");
	    em = emf.createEntityManager();
	}
	
	public Map<Long, Book> findAll() {
		return map.getAll(map.keySet());
	}

	@Override
	public Book findById(Long id) {
		Book result = (Book)map.get(id);
		
		long startTime = System.nanoTime();
		
		// sprawdz czy w cache znajduje sie szukana wartosc, jesli tak to ja zwroc
		if(result != null) {
			System.out.println("Pobrano wartość z cache w czasie " + (System.nanoTime() - startTime)/1000000 + " [ms]");
			return result;
		}
		
		// w przeciwnym przypadku pobierz wartosc z bazy danych
		result = em.createNamedQuery("findBookById", Book.class).setParameter("id", id).getSingleResult();
		
		// jesli znajduje sie w bazie danych to zaktualizuj cache
		if(result != null) {
			System.out.println("Pobrano wartość z bazy danych w czasie " + (System.nanoTime() - startTime)/1000000 + " [ms]");
			map.put(result.getId(), result);
		}
		
		// zwroc pobrana z bazy danych wartosc
		return result;
	}

	@Override
	public void save(Book book) {
		// zapisz do bazy danych
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(book);
	    tx.commit();
	    
	    // zapisz do cache
	    map.put(book.getId(), book);
	}
	
	public void dispose() {
		em.close();
		emf.close();
	}

}
