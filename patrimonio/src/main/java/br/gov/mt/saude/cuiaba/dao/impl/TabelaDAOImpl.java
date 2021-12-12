package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.TabelaDAO;
import br.gov.mt.saude.cuiaba.model.Tabela;

@RequestScoped
public class TabelaDAOImpl extends GenericDAOImpl<Tabela, Integer> implements TabelaDAO{

	private final EntityManager em;

	@Inject
	public TabelaDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public TabelaDAOImpl() {
		this(null);
	}

	@Override
	public List<Tabela> pesquisaTabelaGeral() {
		try {
			return em.createNamedQuery("Tabela.findAll",Tabela.class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public Tabela pesquisaTabelaPorNome(String nome) {
		try {
			return em.createQuery("SELECT t FROM Tabela t WHERE UPPER(t.nomtabela) = UPPER(:pNOME)",Tabela.class)
					.setParameter("pNOME", nome)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaNomeTabela(String nome) {
		try {
			TypedQuery<Tabela> q = em.createQuery("SELECT t FROM Tabela t WHERE UPPER(t.nomtabela) = UPPER(:pNOME)", Tabela.class);
			q.setParameter("pNOME", nome);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

}
