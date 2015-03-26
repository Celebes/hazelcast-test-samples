package io.github.celebes.hazelcast.test.samples;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello Hazelcast!");

	    // utworz klienta Hazelcast
	    ClientConfig clientConfig = new ClientConfig();
	    HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
	    
        // utworz dao dla book
        BookDaoImpl bookDao = new BookDaoImpl(client.getMap("bookMap"));
        
        // utworz kilka obiektow typu book z parametrami i zapisz je
        Random rand = new Random();
        System.out.println("######### SAVING...");
        for(int i=0; i<10; i++) {
        	Book book = new Book(UUID.randomUUID().toString().substring(0, 2), UUID.randomUUID().toString().substring(0, 10), 12.5F, "1-84023-742-2", rand.nextInt(150)+150, rand.nextBoolean());
        	BookParameters bookParams = new BookParameters("secret-" + book.getTitle(), rand.nextDouble(), rand.nextDouble(), rand.nextBoolean());
        	book.setBookParameters(bookParams);
        	bookDao.save(book);
        }
        
        // pobierz Book
        System.out.println("######### LOADING...");
        Book resultThatIsNotInCache = bookDao.findById(1000L);
        Book resultThatIsInCache = bookDao.findById(1L);

        System.out.println("######### " + resultThatIsInCache.getDescription());
        System.out.println("######### " + resultThatIsNotInCache.getDescription());
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
        EntityManager em = emf.createEntityManager();
        
        // pobierz z bazy
        System.out.println("\n*****\nZawartosc bazy danych:\n");
        for(Book b : em.createNamedQuery("findAllBooks", Book.class).getResultList()) {
        	System.out.println("\nBook:");
        	System.out.println(b);
        	System.out.println("Book Parameters:");
        	System.out.println(b.getBookParameters());
        }
        
        System.out.println("\n*****\nZawartosc cache:\n");
        // pobierz z cache
        for(Map.Entry<Long, Book> entry : bookDao.findAll().entrySet()) {
        	System.out.println("\nBook:");
        	System.out.println(entry.getKey() + "/" + entry.getValue());
        	System.out.println("Book Parameters:");
        	System.out.println(((Book)entry.getValue()).getBookParameters());
        }
        
        client.shutdown();
        bookDao.dispose();
	}
}
