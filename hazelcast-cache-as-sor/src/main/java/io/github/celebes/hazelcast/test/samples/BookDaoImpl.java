package io.github.celebes.hazelcast.test.samples;

import com.hazelcast.core.IMap;

public class BookDaoImpl implements BookDao {
	private IMap<Long, Book> map;

	public BookDaoImpl(IMap<Long, Book> map) {
		this.map = map;
	}

	@Override
	public Book findById(Long id) {
		System.out.println("BookDaoImpl -> findById()");
		return (Book) map.get(id);
	}

	@Override
	public void save(Book book) {
		System.out.println("BookDaoImpl -> save()");
		map.put(1L, book);
	}

}
