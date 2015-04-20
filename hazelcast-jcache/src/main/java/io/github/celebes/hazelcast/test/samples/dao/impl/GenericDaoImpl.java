package io.github.celebes.hazelcast.test.samples.dao.impl;

import java.util.Collection;

import io.github.celebes.hazelcast.test.samples.dao.GenericDao;

public class GenericDaoImpl<T> implements GenericDao<T> {
	
	public GenericDaoImpl() {
		
	}

	@Override
	public boolean save(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
