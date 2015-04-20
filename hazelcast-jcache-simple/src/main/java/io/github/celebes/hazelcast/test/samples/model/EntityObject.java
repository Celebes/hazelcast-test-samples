package io.github.celebes.hazelcast.test.samples.model;

import java.io.Serializable;

public interface EntityObject extends Serializable {
	public Long getId();
	public void setId(Long id);
}
