package io.github.celebes.hazelcast.test.samples.cache;

import java.util.Iterator;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

public class GenericCacheEntryListener<K, V> implements
		CacheEntryCreatedListener<K, V>, CacheEntryUpdatedListener<K, V>,
		CacheEntryRemovedListener<K, V>, CacheEntryExpiredListener<K, V> {

	@Override
	public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
		printEvents(cacheEntryEvents);
	}
	
	@Override
	public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
		printEvents(cacheEntryEvents);
	}
	
	@Override
	public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
		printEvents(cacheEntryEvents);
	}
	
	@Override
	public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
		printEvents(cacheEntryEvents);
	}
	
	private void printEvents(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) {
		Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents
				.iterator();
		while (iterator.hasNext()) {
			CacheEntryEvent<? extends K, ? extends V> event = iterator
					.next();
			System.out.println(event.getEventType());
		}
	}

}
