package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.GrupoDAO;
import br.gov.mt.saude.cuiaba.model.Grupo;

@RequestScoped
public class GrupoDAOImpl extends GenericDAOImpl<Grupo, Integer> implements GrupoDAO{

	private final EntityManager em;
	
	@Inject
	public GrupoDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public GrupoDAOImpl() {
		this(null);
	}

	@Override
	public List<Grupo> pesquisaGrupoGeral() {
		try {
			return em.createNamedQuery("Grupo.findAll", Grupo.class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaDescricaoGrupo(String descricao) {
		try {
			TypedQuery<Grupo> q = em.createQuery("SELECT g FROM Grupo g WHERE UPPER(g.descgrupo) = UPPER(:pDESCRICAO)", Grupo.class);
			q.setParameter("pDESCRICAO", descricao);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

}
