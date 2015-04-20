package io.github.celebes.hazelcast.test.samples.cache;

import io.github.celebes.hazelcast.test.samples.dao.GenericDao;

import java.io.Serializable;
import java.util.Collection;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;

public class GenericCacheWriter<K, V> implements CacheWriter<K, V>, Serializable {

	private final GenericDao<V> dao;
	
	public GenericCacheWriter(GenericDao<V> dao) {
		this.dao = dao;
	}
	
	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete((K)key);
	}

	@Override
	public void deleteAll(Collection<?> keys) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Entry<? extends K, ? extends V> entry)
			throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeAll(Collection<Entry<? extends K, ? extends V>> entries)
			throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

}
