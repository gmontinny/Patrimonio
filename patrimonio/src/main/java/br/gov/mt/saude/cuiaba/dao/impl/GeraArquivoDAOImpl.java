package br.gov.mt.saude.cuiaba.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.mt.saude.cuiaba.dao.GeraArquivoDAO;
import br.gov.mt.saude.cuiaba.model.Geraarquivo;

@RequestScoped
public class GeraArquivoDAOImpl extends GenericDAOImpl<Geraarquivo, Integer> implements GeraArquivoDAO{

	private final EntityManager em;

	@Inject
	public GeraArquivoDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public GeraArquivoDAOImpl() {
		this(null);
	}

	@Override
	public Geraarquivo pesquisaUltimoGeraArquivo(int idusuario) {
		try {
			return em.createQuery("SELECT g FROM Geraarquivo g WHERE g.statusgera = 0 AND g.idusuario = :pCodigo ORDER BY g.idgera DESC",Geraarquivo.class)
					.setFirstResult(0)
					.setMaxResults(1)
					.setParameter("pCodigo", idusuario)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
