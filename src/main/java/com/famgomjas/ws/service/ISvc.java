package com.famgomjas.ws.service;

import java.util.List;
import java.util.Optional;

public interface ISvc<T> {
	public Optional<T> get(long id);

	public List<T> getAll();

	public void save(T t);
}
