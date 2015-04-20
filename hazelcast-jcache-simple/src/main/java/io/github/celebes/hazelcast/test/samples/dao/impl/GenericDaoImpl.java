package io.github.celebes.hazelcast.test.samples.dao.impl;

import io.github.celebes.hazelcast.test.samples.dao.GenericDao;
import io.github.celebes.hazelcast.test.samples.model.EntityObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.Cache.Entry;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.event.CacheEntryListener;
import javax.cache.spi.CachingProvider;

public class GenericDaoImpl<T extends EntityObject> implements GenericDao<T> {
	
	private Cache<Long, T> cache;
	private Class<T> type;
	
	public GenericDaoImpl() {
		initType();
		initCache();
	}

	private void initType() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	private void initCache() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();

		String cacheName = type.getSimpleName() + "Cache";

		cache = cacheManager.getCache(cacheName, Long.class, type);

		if (cache == null) {
			System.out.println("Cache: " + cacheName + " doesn't exist..");
			
			CompleteConfiguration<Long, T> config = new MutableConfiguration<Long, T>()
					.setTypes(Long.class, type);

			cache = cacheManager.createCache(cacheName, config);
			System.out.println("Cache: " + cacheName + " created!");
		} else {
			System.out.println("Cache [" + cacheName + "] already exists!");
		}
	}

	@Override
	public void save(T t) {
		cache.put(t.getId(), t);
	}
	
	@Override
	public void update(T t) {
		save(t);
	}

	@Override
	public void delete(T t) {
		cache.remove(t.getId());
	}

	@Override
	public T findById(Long id) {
		return cache.get(id);
	}

	@Override
	public Collection<T> findAll() {
		List<T> result = new ArrayList<>();
		Iterator<Entry<Long, T>> it = cache.iterator();
		
		while(it.hasNext()) {
			result.add(it.next().getValue());
		}
		
		return result;
	}

	public Cache<Long, T> getCache() {
		return cache;
	}

}
