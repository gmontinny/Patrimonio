package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.mt.saude.cuiaba.dao.ArquivoDAO;
import br.gov.mt.saude.cuiaba.model.Arquivo;

@RequestScoped
public class ArquivoDAOImpl extends GenericDAOImpl<Arquivo, Integer> implements ArquivoDAO{

	private final EntityManager em;
	
	@Inject
	public ArquivoDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public ArquivoDAOImpl() {
		this(null);
	}

	@Override
	public List<Arquivo> pesquisaArquivoPorNumero(int numero) {
		try {
			return em.createQuery("SELECT a FROM Arquivo a WHERE a.numeroarquivo = :pNumero ORDER BY a.idarquivos", Arquivo.class)
					.setParameter("pNumero", numero)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
