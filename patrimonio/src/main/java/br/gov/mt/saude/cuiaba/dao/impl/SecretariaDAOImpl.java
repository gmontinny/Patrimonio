package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.SecretariaDAO;
import br.gov.mt.saude.cuiaba.model.Secretaria;

@RequestScoped
public class SecretariaDAOImpl extends GenericDAOImpl<Secretaria, Integer> implements SecretariaDAO{

	private final EntityManager em;

	@Inject
	public SecretariaDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public SecretariaDAOImpl() {
		this(null);
	}

	@Override
	public List<Secretaria> pesquisaSecretariaGeral() {
		return em.createNamedQuery("Secretaria.findAll", Secretaria.class).getResultList();
	}

	@Override
	public boolean validaNomeSecretaria(String nome) {
		try {
			TypedQuery<Secretaria> q = em.createQuery("SELECT s FROM Secretaria s WHERE UPPER(s.nomsecretaria) = UPPER(:pNOME)", Secretaria.class);
			q.setParameter("pNOME", nome);
			return !q.getResultList().isEmpty();
					
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

}
