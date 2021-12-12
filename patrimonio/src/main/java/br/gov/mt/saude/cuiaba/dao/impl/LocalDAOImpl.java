package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.LocalDAO;
import br.gov.mt.saude.cuiaba.model.Local;

@RequestScoped
public class LocalDAOImpl extends GenericDAOImpl<Local, Integer> implements LocalDAO{

	private final EntityManager em;
	
	@Inject
	public LocalDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public LocalDAOImpl() {
		this(null);
	}

	@Override
	public List<Local> pesquisaGeralLocal() {
		return em.createNamedQuery("Local.findAll", Local.class).getResultList();
	}

	@Override
	public boolean validaNomeLocal(int codigo, String nome) {
		try {
			TypedQuery<Local> q = em.createQuery("SELECT l FROM Local l JOIN l.setor s WHERE s.idsetor = :pCodigo AND UPPER(l.desclocal) = UPPER(:pNome)", Local.class);
			q.setParameter("pCodigo", codigo);
			q.setParameter("pNome", nome);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public List<Local> pesquisaLocalPorSetor(int idsetor) {
		try {
			return em.createQuery("SELECT l FROM Local l JOIN l.setor s WHERE s.idsetor = :pCodigo ", Local.class)
					.setParameter("pCodigo", idsetor)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public Local pesquisaLocalPorCodigoESetor(int codigo, int idsetor) {
		try {
			return em.createQuery("SELECT l FROM Local l JOIN l.setor s WHERE l.idlocal = :pCodigo AND s.idsetor = :pIdsetor ", Local.class)
					.setParameter("pCodigo",codigo)
					.setParameter("pIdsetor",idsetor)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
