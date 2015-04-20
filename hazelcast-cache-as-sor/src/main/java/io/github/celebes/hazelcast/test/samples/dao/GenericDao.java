package io.github.celebes.hazelcast.test.samples.dao;

import io.github.celebes.hazelcast.test.samples.EntityObject;

import java.util.Collection;

public interface GenericDao<T extends EntityObject> {
	void save(T t);
	void delete(T t);
	void update(T t);
	T findById(Long id);
	Collection<T> findAll();
}
