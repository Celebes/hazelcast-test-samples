package io.github.celebes.hazelcast.test.samples;

import java.util.List;

public interface BookDao {
	public Book findById(Long id);
	public void save(Book b);
}
