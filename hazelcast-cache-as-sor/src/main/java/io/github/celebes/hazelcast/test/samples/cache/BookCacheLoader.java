package io.github.celebes.hazelcast.test.samples.cache;

import io.github.celebes.hazelcast.test.samples.dao.impl.GenericJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.dao.impl.jpa.BookJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.model.Book;

import java.io.Serializable;
import java.util.Map;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

public class BookCacheLoader implements CacheLoader<Long, Book>, Serializable {

	public BookCacheLoader(GenericJpaDaoImpl<Book> jpaDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book load(Long key) throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Book> loadAll(Iterable<? extends Long> keys)
			throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}

}
