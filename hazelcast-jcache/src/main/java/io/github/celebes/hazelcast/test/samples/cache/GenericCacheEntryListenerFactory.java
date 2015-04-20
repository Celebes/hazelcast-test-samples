package io.github.celebes.hazelcast.test.samples.cache;

import javax.cache.configuration.Factory;

public class GenericCacheEntryListenerFactory<K, V> implements Factory<GenericCacheEntryListener<K, V>> {

	private static final long serialVersionUID = -5604595714717922668L;

	@Override
	public GenericCacheEntryListener<K, V> create() {
		return new GenericCacheEntryListener<K, V>();
	}

}
