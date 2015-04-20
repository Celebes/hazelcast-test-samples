package io.github.celebes.hazelcast.test.samples;

import java.io.Serializable;

public interface EntityObject extends Serializable {
	Long getId();
	void setId(Long id);
}
