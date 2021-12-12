package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.MaterialDAO;
import br.gov.mt.saude.cuiaba.model.Material;

@RequestScoped
public class MaterialDAOImpl extends GenericDAOImpl<Material, Integer> implements MaterialDAO{
	private final EntityManager em;
	
	@Inject
	public MaterialDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public MaterialDAOImpl() {
		this(null);
	}

	@Override
	public List<Material> pesquisaGeralMaterial() {
		return em.createNamedQuery("Material.findAll", Material.class).getResultList();
	}

	@Override
	public boolean validaDescricaoMaterial(int idsubgrupo , String descricao) {
		try {
			TypedQuery<Material> q = em.createQuery("SELECT l FROM Material l JOIN l.subgrupo s WHERE s.idsubgrupo = :pCodigo AND  UPPER(l.descmaterial) = UPPER(:pNome)", Material.class);
			q.setParameter("pCodigo", idsubgrupo);
			q.setParameter("pNome", descricao);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public List<Material> pesquisaMaterialPorSubGrupo(int idsubgrupo) {
		try {
			return em.createQuery("SELECT l FROM Material l JOIN l.subgrupo s WHERE s.idsubgrupo = :pCodigo ", Material.class)
					.setParameter("pCodigo", idsubgrupo)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public Material pesquisaMaterialPorCodigoESubgrupo(int codigo, int idsubgrupo) {
		try {
			return em.createQuery("SELECT m FROM Material m JOIN m.subgrupo s WHERE m.idmaterial = :pCodigo AND s.idsubgrupo = :pIdsubgrupo ", Material.class)
					.setParameter("pCodigo", codigo)
					.setParameter("pIdsubgrupo", idsubgrupo)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
