package com.videos.repository;

public abstract class GenericRepository {

	public abstract <T> Object create(T t);

	public abstract <T> Object delete(T t);

	public abstract <T> Object find(T t);

	public abstract <T> Object update(T t);

	public abstract <T> Iterable<T> findAll();

}
