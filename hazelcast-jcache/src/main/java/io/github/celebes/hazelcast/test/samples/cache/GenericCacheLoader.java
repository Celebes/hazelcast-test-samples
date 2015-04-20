package io.github.celebes.hazelcast.test.samples.cache;

import java.io.Serializable;
import java.util.Map;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

public class GenericCacheLoader<K, V> implements CacheLoader<K, V>, Serializable {

	private static final long serialVersionUID = -524809888615403300L;

	@Override
	public V load(K arg0) throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<K, V> loadAll(Iterable<? extends K> arg0)
			throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
