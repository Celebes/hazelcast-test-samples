package io.github.celebes.hazelcast.test.samples.dao.impl.hazelcast;

import io.github.celebes.hazelcast.test.samples.cache.BookCacheLoader;
import io.github.celebes.hazelcast.test.samples.cache.BookCacheWriter;
import io.github.celebes.hazelcast.test.samples.dao.impl.GenericCacheDaoImpl;
import io.github.celebes.hazelcast.test.samples.dao.impl.GenericJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.dao.impl.jpa.BookJpaDaoImpl;
import io.github.celebes.hazelcast.test.samples.model.Book;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class BookCacheDaoImpl extends GenericCacheDaoImpl<Book> {
	
	public BookCacheDaoImpl(BookJpaDaoImpl jpaDao) {
		initCache(jpaDao);
	}

	@Override
	protected void initCache(GenericJpaDaoImpl<Book> jpaDao) {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();

		String cacheName = type.getSimpleName() + "Cache";

		cache = cacheManager.getCache(cacheName, Long.class, type);

		if (cache == null) {
			System.out.println("Cache: " + cacheName + " doesn't exist..");
			
			CompleteConfiguration<Long, Book> config = new MutableConfiguration<Long, Book>()
					.setTypes(Long.class, type)
					.setReadThrough(true)
					.setWriteThrough(true)
					.setCacheLoaderFactory(FactoryBuilder.factoryOf(new BookCacheLoader(jpaDao)))
					.setCacheWriterFactory(FactoryBuilder.factoryOf(new BookCacheWriter(jpaDao)));

			cache = cacheManager.createCache(cacheName, config);
			System.out.println("Cache: " + cacheName + " created!");
		} else {
			System.out.println("Cache [" + cacheName + "] already exists!");
		}
	}

	@Override
	protected void initCacheEntryListener() {
		
	}

}
