package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.SetorDAO;
import br.gov.mt.saude.cuiaba.model.Setor;

@RequestScoped
public class SetorDAOImpl extends GenericDAOImpl<Setor, Integer> implements SetorDAO{

	private final EntityManager em;
	
	@Inject
	public SetorDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public SetorDAOImpl() {
		this(null);
	}

	@Override
	public List<Setor> pesquisaGeralSetor() {		
		return em.createNamedQuery("Setor.findAll", Setor.class).getResultList();
	}

	@Override
	public List<Setor> pesquisaSetorPorSecretaria(int idsecretaria) {
		try {
			return em.createQuery("SELECT s FROM Setor s JOIN s.secretaria se WHERE se.idsecretaria = :pCodigo", Setor.class)
					.setParameter("pCodigo", idsecretaria)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaNomeSetor(String nome) {
		try {
			TypedQuery<Setor> q = em.createQuery("SELECT s FROM Setor s WHERE UPPER(s.nomsetor) = UPPER(:pNome)", Setor.class);
			q.setParameter("pNome", nome);
			return !q.getResultList().isEmpty();
			
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public Setor pesquisaSetorPorCodigoESecretaria(int codigo, int idsecretaria) {
		try {
			return em.createQuery("SELECT s FROM Setor s JOIN s.secretaria se WHERE s.idsetor = :pCodigo AND se.idsecretaria = :pIdsecretaria",Setor.class)
					.setParameter("pCodigo", codigo)
					.setParameter("pIdsecretaria", idsecretaria)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
