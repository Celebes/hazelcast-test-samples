package io.github.celebes.hazelcast.test.samples.cache;

import io.github.celebes.hazelcast.test.samples.dao.impl.GenericJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.dao.impl.jpa.BookJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.model.Book;

import java.io.Serializable;
import java.util.Collection;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;

public class BookCacheWriter implements CacheWriter<Long, Book>, Serializable {

	private GenericJpaDaoImpl<Book> bookJpaDaoImpl;
	
	public BookCacheWriter(GenericJpaDaoImpl<Book> jpaDao) {
		this.bookJpaDaoImpl = jpaDao;
	}

	@Override
	public void write(Entry<? extends Long, ? extends Book> entry) throws CacheWriterException {
		System.out.println("CACHE WRITER - WRITE");
		System.out.println("ID ENTRY PRZED = " + entry.getKey());
		System.out.println("ID BOOK PRZED = " + entry.getValue().getId());
		bookJpaDaoImpl.save(entry.getValue());
		System.out.println("ID ENTRY PO = " + entry.getKey());
		System.out.println("ID BOOK PO = " + entry.getValue().getId());
	}

	@Override
	public void writeAll(Collection<Entry<? extends Long, ? extends Book>> entries) throws CacheWriterException {
		System.out.println("CACHE WRITER - WRITE ALL");
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		System.out.println("CACHE WRITER - DELETE");
	}

	@Override
	public void deleteAll(Collection<?> keys) throws CacheWriterException {
		System.out.println("CACHE WRITER - DELETE ALL");
	}

}
