package io.github.celebes.hazelcast.test.samples.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericDaoImpl<T> {
	protected Class<T> type;
	
	public GenericDaoImpl() {
		initType();
	}
	
	private void initType() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	public Class<T> getType() {
		return type;
	}
}
