package io.github.celebes.hazelcast.test.samples.dao;

import java.util.Collection;

public interface GenericDao<T> {
	boolean save(T t);
	boolean delete(T t);
	T findById(long id);
	boolean update(T t);
	Collection<T> findAll();
}
