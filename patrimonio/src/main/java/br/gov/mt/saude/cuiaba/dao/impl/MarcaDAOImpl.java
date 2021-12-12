package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.MarcaDAO;
import br.gov.mt.saude.cuiaba.model.Marca;

@RequestScoped
public class MarcaDAOImpl extends GenericDAOImpl<Marca, Integer> implements MarcaDAO{
	
	private final EntityManager em;
	
	@Inject
	public MarcaDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public MarcaDAOImpl() {
		this(null);
	}

	@Override
	public List<Marca> pesquisaMarcaGeral() {
		try {
			return em.createNamedQuery("Marca.findAll", Marca.class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaDescricaoMarca(String descricao) {
		try {
			TypedQuery<Marca> q = em.createQuery("SELECT m FROM Marca m WHERE UPPER(m.descMarca) = UPPER(:pDESCRICAO)", Marca.class);
			q.setParameter("pDESCRICAO", descricao);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

}
