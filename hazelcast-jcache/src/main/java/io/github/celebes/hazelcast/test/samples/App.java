package io.github.celebes.hazelcast.test.samples;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello Hazelcast!");
		
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();
		
		/*CompleteConfiguration<String, String> config =
			    new MutableConfiguration<String, String>()
			        .setTypes( String.class, String.class );
		
		Cache<String, String> cache = cacheManager.createCache( "example", config );*/
		
		Cache<String, String> cache = cacheManager.getCache( "example", String.class, String.class );
		
		cache.put( "world", "Hello World" );

		// Retrieve the value again from the cache
		String value = cache.get( "world" );

		// Print the value 'Hello World'
		System.out.println( value );
	}
}
