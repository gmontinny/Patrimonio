package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.SubgrupoDAO;
import br.gov.mt.saude.cuiaba.model.Subgrupo;

@RequestScoped
public class SubGrupoDAOImpl extends GenericDAOImpl<Subgrupo, Integer> implements SubgrupoDAO{
	
	private final EntityManager em;
	
	@Inject
	public SubGrupoDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public SubGrupoDAOImpl() {
		this(null);
	}

	@Override
	public List<Subgrupo> pesquisaGeralSubGrupo() {		
		return em.createNamedQuery("Subgrupo.findAll", Subgrupo.class).getResultList();
	}

	@Override
	public List<Subgrupo> pesquisaSubgrupoPorGrupo(int idgrupo) {
		try {
			return em.createQuery("SELECT s FROM Subgrupo s JOIN s.grupo g WHERE g.idgrupo = :pCodigo", Subgrupo.class)
					.setParameter("pCodigo", idgrupo)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaNomeSubGrupo(String nome) {
		try {
			TypedQuery<Subgrupo> q = em.createQuery("SELECT s FROM Subgrupo s WHERE UPPER(s.descsubgrupo) = UPPER(:pNome)", Subgrupo.class);
			q.setParameter("pNome", nome);
			return !q.getResultList().isEmpty();
			
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public Subgrupo pesquisaSubGrupoPorCodigoEGrupo(int codigo, int idgrupo) {
		try {
			return em.createQuery("SELECT s FROM Subgrupo s JOIN s.grupo g WHERE s.idsubgrupo = :pCodigo AND g.idgrupo = :pIdgrupo", Subgrupo.class)
					.setParameter("pCodigo", codigo)
					.setParameter("pIdgrupo", idgrupo)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}
}
