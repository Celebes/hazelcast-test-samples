package io.github.celebes.hazelcast.test.samples.dao.impl;

import io.github.celebes.hazelcast.test.samples.EntityObject;
import io.github.celebes.hazelcast.test.samples.cache.BookCacheLoader;
import io.github.celebes.hazelcast.test.samples.dao.GenericDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.Cache.Entry;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import javax.cache.spi.CachingProvider;

/*
 * Zajmuje się zapisem do cache
 */

public abstract class GenericCacheDaoImpl<T extends EntityObject> extends GenericDaoImpl<T> implements GenericDao<T> {
	protected Cache<Long, T> cache;

	protected abstract void initCache(GenericJpaDaoImpl<T> jpaDao);
	protected abstract void initCacheEntryListener();

	@Override
	public void save(T t) {
		System.out.println("CACHE DAO - SAVE");
		System.out.println("ID PRZED = " + t.getId());
		cache.put(-1L, t);
		System.out.println("ID PO = " + t.getId());
	}

	@Override
	public void delete(T t) {
		System.out.println("CACHE DAO - DELETE");
		cache.remove(t.getId());
	}

	@Override
	public T findById(Long id) {
		System.out.println("CACHE DAO - FIND BY ID");
		return cache.containsKey(id) ? cache.get(id) : null;
	}

	@Override
	public void update(T t) {
		System.out.println("CACHE DAO - UPDATE");
		save(t);
	}

	@Override
	public List<T> findAll() {
		System.out.println("CACHE DAO - FIND ALL");
		List<T> result = new ArrayList<>();
		Iterator<Entry<Long, T>> it = cache.iterator();
		
		while(it.hasNext()) {
			result.add(it.next().getValue());
		}
		
		return result;
	}
	
}
