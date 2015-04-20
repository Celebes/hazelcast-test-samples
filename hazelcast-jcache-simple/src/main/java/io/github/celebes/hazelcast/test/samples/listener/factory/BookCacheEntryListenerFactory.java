package io.github.celebes.hazelcast.test.samples.listener.factory;

import io.github.celebes.hazelcast.test.samples.listener.BookCacheEntryListener;
import io.github.celebes.hazelcast.test.samples.model.Book;

import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryListener;

public class BookCacheEntryListenerFactory implements Factory<CacheEntryListener<Long, Book>> {

	@Override
	public CacheEntryListener<Long, Book> create() {
		return new BookCacheEntryListener();
	}

}
