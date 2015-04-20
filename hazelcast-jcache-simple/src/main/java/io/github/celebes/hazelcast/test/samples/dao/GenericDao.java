package io.github.celebes.hazelcast.test.samples.dao;

import java.util.Collection;

public interface GenericDao<T> {
	void save(T t);
	void delete(T t);
	void update(T t);
	T findById(Long id);
	Collection<T> findAll();
}
