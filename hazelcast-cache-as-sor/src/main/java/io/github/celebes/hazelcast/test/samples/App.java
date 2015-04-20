package io.github.celebes.hazelcast.test.samples;

import io.github.celebes.hazelcast.test.samples.dao.impl.hazelcast.BookCacheDaoImpl;
import io.github.celebes.hazelcast.test.samples.dao.impl.jpa.BookJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.model.Book;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello Hazelcast!");
		
		// weld
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		
	    // utworz obiekt typu Book
	    Book book = new Book("HP", "Harry Potter", 12.5F, "1-84023-742-2", 354, false);
	    
	    // utworz klienta Hazelcast
	    ClientConfig clientConfig = new ClientConfig();
	    HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
	    
	    // utworz cache dao
	    BookJpaDaoImpl jpaDao = new BookJpaDaoImpl();
	    BookCacheDaoImpl dao = new BookCacheDaoImpl(jpaDao);
	    
	    dao.save(book);
	}
	/*public static void main(String[] args) {
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
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-db");
        EntityManager em = emf.createEntityManager();
        
        for(Book b : em.createNamedQuery("findAllBooks", Book.class).getResultList()) {
        	System.out.println(b);
        }
	}*/
}
