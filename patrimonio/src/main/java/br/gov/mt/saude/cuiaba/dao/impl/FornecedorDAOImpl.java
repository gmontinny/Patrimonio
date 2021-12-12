package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.FornecedorDAO;
import br.gov.mt.saude.cuiaba.model.Fornecedor;

@RequestScoped
public class FornecedorDAOImpl extends GenericDAOImpl<Fornecedor, Integer> implements FornecedorDAO{

	private final EntityManager em;

	@Inject
	public FornecedorDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public FornecedorDAOImpl() {
		this(null);
	}

	@Override
	public List<Fornecedor> pesquisaFornecedorGeral() {
		try {
			return em.createNamedQuery("Fornecedor.findAll", Fornecedor.class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaCNPJFonecedor(String cnpj) {
		try {
			TypedQuery<Fornecedor> q = em.createQuery("SELECT f FROM Fornecedor f WHERE f.cnpjfornecedor = pCNPJ", Fornecedor.class);
			q.setParameter("pCNPJ", cnpj);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

}
