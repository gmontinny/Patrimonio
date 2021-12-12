package br.gov.mt.saude.cuiaba.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDAO <T, I extends Serializable> {
	public T getById(Class<T> classe, I pk);
	public T save(T entity);
	public T update(T entity);
	public void delete(T entity);
	public List<T> getAll(Class<T> classe);
}
