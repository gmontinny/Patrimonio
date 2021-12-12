package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.ModeloDAO;
import br.gov.mt.saude.cuiaba.model.Modelo;

@RequestScoped
public class ModeloDAOImpl extends GenericDAOImpl<Modelo, Integer> implements ModeloDAO{
	private final EntityManager em;
	
	@Inject
	public ModeloDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public ModeloDAOImpl() {
		this(null);
	}
	
	@Override
	public List<Modelo> pesquisaGeralModelo() {		
		return em.createNamedQuery("Modelo.findAll", Modelo.class).getResultList();
	}
	
	@Override
	public List<Modelo> pesquisaModeloPorMarca(int idmarca) {
		try {
			return em.createQuery("SELECT s FROM Modelo s JOIN s.marca g WHERE g.idmarca = :pCodigo", Modelo.class)
					.setParameter("pCodigo", idmarca)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}
	
	@Override
	public boolean validaNomeModelo(String nome) {
		try {
			TypedQuery<Modelo> q = em.createQuery("SELECT s FROM Modelo s WHERE UPPER(s.descModelo) = UPPER(:pNome)", Modelo.class);
			q.setParameter("pNome", nome);
			return !q.getResultList().isEmpty();
			
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public Modelo pesquisaModeloPorCodigoEMarca(int codigo, int idmarca) {
		try {
			return em.createQuery("SELECT m FROM Modelo m JOIN m.marca ma WHERE m.idmodelo = :pCodigo AND ma.idmarca = :pIdmarca", Modelo.class)
					.setParameter("pCodigo", codigo)
					.setParameter("pIdmarca", idmarca)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
