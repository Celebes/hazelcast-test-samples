package io.github.celebes.hazelcast.test.samples.listener.factory;

import io.github.celebes.hazelcast.test.samples.listener.UserCacheEntryListener;
import io.github.celebes.hazelcast.test.samples.model.User;

import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryListener;

public class UserCacheEntryListenerFactory implements Factory<CacheEntryListener<Long, User>> {

	@Override
	public CacheEntryListener<Long, User> create() {
		return new UserCacheEntryListener();
	}
	
}
