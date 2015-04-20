package io.github.celebes.hazelcast.test.samples;

import javax.cache.configuration.MutableCacheEntryListenerConfiguration;

import io.github.celebes.hazelcast.test.samples.dao.impl.BookDaoImpl;
import io.github.celebes.hazelcast.test.samples.listener.factory.BookCacheEntryListenerFactory;
import io.github.celebes.hazelcast.test.samples.model.Book;

public class Main {
	public static void main(String[] args) {
		// w projekcie jest dodana paczka -client, wiec trzeba recznie przestawic na server zeby nie mieszal
		System.setProperty("hazelcast.jcache.provider.type", "server");

		BookDaoImpl bookDao = new BookDaoImpl();
		
		bookDao.getCache().registerCacheEntryListener(
				new MutableCacheEntryListenerConfiguration<Long, Book>(
						new BookCacheEntryListenerFactory(), null, true, true
					)
				);
		
		Book book = new Book("Harry Potter", "raz dwa trzy cztery", 12.5F, "123123123", 333, false);
		book.setId(1L);
		
		bookDao.save(book);
		
		System.out.println(bookDao.findById(1L));
		
		BookDaoImpl bookDao2 = new BookDaoImpl();
		
		Book book2 = new Book("Harry Potter 2", "raz dwa trzy cztery", 12.5F, "123123123", 333, false);
		book2.setId(2L);
		
		bookDao.save(book2);
		
		System.out.println("Wszystkie ksiunszki:");
		for(Book b : bookDao.findAll()) {
			System.out.println(b);
		}
		
		bookDao.delete(book2);
		
		System.out.println("Wszystkie ksiunszki:");
		for(Book b : bookDao.findAll()) {
			System.out.println(b);
		}
	}
}
