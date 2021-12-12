package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.BaixaDAO;
import br.gov.mt.saude.cuiaba.model.Baixa;
import br.gov.mt.saude.cuiaba.model.Local;

@RequestScoped
public class BaixaDAOImpl extends GenericDAOImpl<Baixa, Integer> implements BaixaDAO{

	private final EntityManager em;
	
	@Inject
	public BaixaDAOImpl(EntityManager em) {
		super(em);
		this.em = em;		
	}

	public BaixaDAOImpl() {
		this(null);
	}

	@Override
	public List<Object[]> pesquisaBaixaGeral() {
		try {
			return em.createQuery(" SELECT p.idpatrimonio, p.serialpatrimonio, l.idlocal, l.desclocal, s.idsetor, s.nomsetor, se.idsecretaria, se.nomsecretaria, b.idbaixa " + 
					" FROM Baixa b, Patrimonio p, Local l, Setor s, Secretaria se " + 
					" WHERE b.idpatrimonio = p.idpatrimonio " + 
					" AND p.local.idlocal = l.idlocal " + 
					" AND s.idsetor = l.setor.idsetor " + 
					" AND se.idsecretaria = s.secretaria.idsecretaria " + 
					" AND p.statusbaixa = 1 ", Object[].class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public List<Baixa> pesquisaBaixaPorPatrimonioESerial(String patrimonio, String serial) {
		try {
			return em.createQuery("SELECT b FROM Baixa b, Patrimonio p"
					+ " WHERE b.idpatrimonio = p.idpatrimonio "
					+ " AND p.statusbaixa = 1 "
					+ " AND (p.idpatrimonio = :pNUMERO OR p.serialpatrimonio = :pSERIAL)", Baixa.class)
					.setParameter("pNUMERO", patrimonio)
					.setParameter("pSERIAL", serial)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public List<Object[]> pesquisaBaixaPorLocal(Local local) {
		try {
			return em.createQuery(" SELECT p.idpatrimonio, p.serialpatrimonio, l.idlocal, l.desclocal, s.idsetor, s.nomsetor, se.idsecretaria, se.nomsecretaria, b.idbaixa,"
					+ " g.descgrupo, sb.descsubgrupo, m.descmaterial, ma.descmarca, mo.descmodelo, f.razaosocial "
					+ " FROM Baixa b, Patrimonio p, Local l, Setor s, Secretaria se, Grupo g, Subgrupo sb, Material m, Marca ma, Modelo mo, Fornecedor f "
					+ " WHERE b.idpatrimonio = p.idpatrimonio "
					+ " AND p.local.idlocal = l.idlocal "
					+ " AND s.idsetor = l.setor.idsetor "
					+ " AND se.idsecretaria = s.secretaria.idsecretaria "
					+ " AND p.material.idmaterial = m.idmaterial "
					+ " AND p.modelo.idmodelo = mo.idmodelo "
					+ " AND p.fornecedor.idfornecedor = f.idfornecedor "
					+ " AND m.subgrupo.idsubgrupo = sb.idsubgrupo "
					+ " AND g.idgrupo = sb.grupo.idgrupo "
					+ " AND ma.idmarca = mo.marca.idmarca "
					+ " AND p.statusbaixa = 1 "
					+ " AND l.idlocal = :pCODIGO ", Object[].class)
					.setParameter("pCODIGO", local.getIdlocal())
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaBaixaPorPatrimonio(String numero) {
		try {
			TypedQuery<Baixa> q = em.createQuery("SELECT b FROM Baixa b WHERE b.idpatrimonio = :pNUMERO", Baixa.class);
			q.setParameter("pNUMERO", numero);
			return !q.getResultList().isEmpty();
					
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public List<Baixa> pesquisaBaixaMobile() {
		try {
			return em.createQuery("SELECT b FROM Baixa b ORDER BY b.idbaixa", Baixa.class)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
