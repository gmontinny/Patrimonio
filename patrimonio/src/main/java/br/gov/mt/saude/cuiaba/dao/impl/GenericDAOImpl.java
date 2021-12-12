package br.gov.mt.saude.cuiaba.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import br.gov.mt.saude.cuiaba.dao.GenericDAO;

public class GenericDAOImpl<T, I extends Serializable> implements GenericDAO<T, I>{
	
	private EntityManager em;
	
	
	public GenericDAOImpl(EntityManager em) {
		this.em = em;		
	}
	
	
	
	@Override
	public T getById(Class<T> classe, I pk) {
		T t  = em.find(classe, pk);
		return t;
	}

	@Override
	public T update(T entity) {
		T updated = null;
		try {
			em.merge(entity);
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}
		
		return updated;				
	}

	@Override
	public void delete(T entity) {
		try {
			em.remove(em.contains(entity) ? entity : em.merge(entity));
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}	
	}
	
	@Override
	public T save(T entity) {
		T saved = null;
		try {
			em.persist(entity);
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}
		
		return saved;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> classe) {
		List<T> t = em.createQuery("select s from " + classe.getSimpleName() + " s").getResultList();
		return t;
	}



}

