package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.PatrimonioDAO;
import br.gov.mt.saude.cuiaba.model.Patrimonio;

@RequestScoped
public class PatrimonioDAOImpl extends GenericDAOImpl<Patrimonio, Integer> implements PatrimonioDAO{

	private final EntityManager em;
	
	@Inject
	public PatrimonioDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public PatrimonioDAOImpl() {
		this(null);
	}

	@Override
	public List<Patrimonio> pesquisaPatrimonioGeral(Integer idsecretaria, Integer idsetor, Integer idlocal,
			Integer idgrupo, Integer idsubgrupo, Integer idmaterial, Integer idmarca, Integer idmodelo,
			Integer fornecedor, Integer situacao, Integer baixa, String numeroPatrimonio, String serial) {
		try {
			
			String sql = "";
			
			if(idsecretaria != null && idsecretaria != 0) {
				sql += " AND l.setor.secretaria.idsecretaria = " + idsecretaria;
			}
			
			if(idsetor != null && idsetor != 0) {
				sql += " AND l.setor.idsetor = " + idsetor;
			}
			
			if(idlocal != null && idlocal != 0) {
				sql += " AND l.idlocal = " + idlocal;
			}
			
			if(idgrupo != null && idgrupo != 0) {
				sql += " AND m.subgrupo.grupo.idgrupo = " + idgrupo;
			}

			if(idsubgrupo != null && idsubgrupo != 0) {
				sql += " AND m.subgrupo.idsubgrupo = " + idsubgrupo;
			}
			
			if(idmaterial != null && idmaterial != 0) {
				sql += " AND m.idmaterial = " + idmaterial;
			}
			
			if(idmarca != null && idmarca != 0) {
				sql += " AND mo.marca.idmarca = " + idmarca;
			}

			if(idmodelo != null && idmodelo != 0) {
				sql += " AND mo.idmodelo = " + idmodelo;
			}
			
			if(fornecedor != null && fornecedor != 0) {
				sql += " AND f.idfornecedor = " + fornecedor;
			}
			
			if(numeroPatrimonio != null) {
				sql += " AND TRIM(p.idpatrimonio) = TRIM('" + numeroPatrimonio + "')";
			}

			if(serial != null) {
				sql += " AND UPPER(TRIM(p.serialpatrimonio)) = UPPER(TRIM('" + serial + "'))";
			}

			if(situacao != null && situacao != 0) {
				sql += " AND p.situacaopatrimonio = " + situacao;
			}

			if(baixa != null && baixa != 0) {
				sql += " AND p.statusbaixa = " + baixa;
			}
			
			return em.createQuery(" SELECT p FROM Patrimonio p, Material m, Modelo mo, Local l, Fornecedor f "
					+ " WHERE p.material.idmaterial = m.idmaterial"
					+ " AND p.modelo.idmodelo = mo.idmodelo "
					+ " AND p.local.idlocal = l.idlocal "
					+ " AND p.fornecedor.idfornecedor = f.idfornecedor " + 
					sql + 
					" ORDER BY l.setor.secretaria.idsecretaria , l.setor.idsetor , l.idlocal ", Patrimonio.class)
					.getResultList();
			
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaNumeroPatrimonio(String numeroPatrimonio) {
		try {
			TypedQuery<Patrimonio> q = em.createQuery("SELECT p FROM Patrimonio p WHERE p.idpatrimonio = :pNUMERO", Patrimonio.class);
			q.setParameter("pNUMERO", numeroPatrimonio);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public boolean validaNumeroSerial(String numeroSerial) {
		try {
			TypedQuery<Patrimonio> q = em.createQuery("SELECT p FROM Patrimonio p WHERE p.serialpatrimonio = :pSERIAL", Patrimonio.class);
			q.setParameter("pSERIAL", numeroSerial);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public Patrimonio pesquisaPorNumeroPatrimonio(String numeroPatrimonio) {
		try {
			return em.createQuery("SELECT p FROM Patrimonio p WHERE p.idpatrimonio = :pNUMERO", Patrimonio.class)
					.setParameter("pNUMERO", numeroPatrimonio)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
