package io.github.celebes.hazelcast.test.samples;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello Hazelcast!");
		
	    // Utworz obiekt typu Book
	    Book book = new Book("HP", "Harry Potter", 12.5F, "1-84023-742-2", 354, false);
	    
	    // utworz klienta Hazelcast
	    ClientConfig clientConfig = new ClientConfig();
	    HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        
        // pobierz cache
        IMap map = client.getMap("bookMap");
	    
        // zapisz obiekt typu Book
        System.out.println("######### SAVING...");
        BookDaoImpl bookDao = new BookDaoImpl(map);
        bookDao.save(book);
        
        // pobierz Book
        System.out.println("######### LOADING...");
        Book resultThatIsInCache = bookDao.findById(1L);
        Book resultThatIsNotInCache = bookDao.findById(1000L);

        System.out.println("######### " + resultThatIsInCache.getDescription());
        System.out.println("######### " + resultThatIsNotInCache.getDescription());
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
        EntityManager em = emf.createEntityManager();
        
        for(Book b : em.createNamedQuery("findAllBooks", Book.class).getResultList()) {
        	System.out.println(b);
        }
	}
}
