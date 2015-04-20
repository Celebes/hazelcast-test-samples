package io.github.celebes.hazelcast.test.samples.cdi;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Singleton
public class DatabaseProducer {

	/*@Default
	EntityManager em = null;
	
	@Produces
	@Default
	private EntityManager produceEntityManager(InjectionPoint injectionPoint) {
		System.out.println("Default DB Producer");
		em = Persistence.createEntityManagerFactory("main-db").createEntityManager();
		return em;
	}
	
	@Default
	private void closeEntityManager(@Disposes @Default EntityManager em) {
		System.out.println("Default DB Disposer");
		if (em != null) em.close();
	}*/
	
}
