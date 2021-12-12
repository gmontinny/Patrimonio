package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.mt.saude.cuiaba.dao.TransferenciaDAO;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Transferencia;

@RequestScoped
public class TransferenciaDAOImpl extends GenericDAOImpl<Transferencia, Integer> implements TransferenciaDAO {

	private final EntityManager em;
	
	@Inject
	public TransferenciaDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public TransferenciaDAOImpl() {
		this(null);
	}

	@Override
	public List<Object[]> pesquisaTransferenciaGeral() {
		try {
			return em.createQuery(" SELECT p.idpatrimonio, p.serialpatrimonio, l.idlocal, l.desclocal, s.idsetor, s.nomsetor, se.idsecretaria, se.nomsecretaria, t.idtransferencia " + 
					" FROM Transferencia t, Patrimonio p, Local l, Setor s, Secretaria se " + 
					" WHERE t.idpatrimonio = p.idpatrimonio " + 
					" AND p.local.idlocal = l.idlocal " + 
					" AND s.idsetor = l.setor.idsetor " + 
					" AND se.idsecretaria = s.secretaria.idsecretaria" +
					" ORDER BY t.datatransferencia DESC ", Object[].class)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public Transferencia pesquisaTransferencia(String numeroPatrimonio) {
		try {
			return em.createQuery("SELECT t FROM Transferencia t WHERE t.idpatrimonio = :pNUMERO", Transferencia.class)
					.setParameter("pNUMERO", numeroPatrimonio)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public List<Object[]> pesquisaTransferenciaRealizada(int codigo) {
		try {
			return em.createQuery(" SELECT p.idpatrimonio, p.serialpatrimonio, l.idlocal, l.desclocal, s.idsetor, s.nomsetor, se.idsecretaria, se.nomsecretaria, t.idtransferencia,"
					+ " g.descgrupo, sb.descsubgrupo, m.descmaterial, ma.descmarca, mo.descmodelo, f.razaosocial, t.idlocalorigem, t.idlocaldestino, t.usuario.idusuario "
					+ " FROM Transferencia t, Patrimonio p, Local l, Setor s, Secretaria se, Grupo g, Subgrupo sb, Material m, Marca ma, Modelo mo, Fornecedor f "
					+ " WHERE t.idpatrimonio = p.idpatrimonio "
					+ " AND p.local.idlocal = l.idlocal "
					+ " AND s.idsetor = l.setor.idsetor "
					+ " AND se.idsecretaria = s.secretaria.idsecretaria "
					+ " AND p.material.idmaterial = m.idmaterial "
					+ " AND p.modelo.idmodelo = mo.idmodelo "
					+ " AND p.fornecedor.idfornecedor = f.idfornecedor "
					+ " AND m.subgrupo.idsubgrupo = sb.idsubgrupo "
					+ " AND g.idgrupo = sb.grupo.idgrupo "
					+ " AND ma.idmarca = mo.marca.idmarca "					
					+ " AND t.idtransferencia = :pCODIGO ", Object[].class)
					.setParameter("pCODIGO", codigo)
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public List<Object[]> pesquisaTransferenciaLocal(Local local) {
		try {
			return em.createQuery(" SELECT p.idpatrimonio, p.serialpatrimonio, l.idlocal, l.desclocal, s.idsetor, s.nomsetor, se.idsecretaria, se.nomsecretaria, t.idtransferencia,"
					+ " g.descgrupo, sb.descsubgrupo, m.descmaterial, ma.descmarca, mo.descmodelo, f.razaosocial, t.idlocalorigem, t.idlocaldestino, t.usuario.idusuario "
					+ " FROM Transferencia t, Patrimonio p, Local l, Setor s, Secretaria se, Grupo g, Subgrupo sb, Material m, Marca ma, Modelo mo, Fornecedor f "
					+ " WHERE t.idpatrimonio = p.idpatrimonio "
					+ " AND p.local.idlocal = l.idlocal "
					+ " AND s.idsetor = l.setor.idsetor "
					+ " AND se.idsecretaria = s.secretaria.idsecretaria "
					+ " AND p.material.idmaterial = m.idmaterial "
					+ " AND p.modelo.idmodelo = mo.idmodelo "
					+ " AND p.fornecedor.idfornecedor = f.idfornecedor "
					+ " AND m.subgrupo.idsubgrupo = sb.idsubgrupo "
					+ " AND g.idgrupo = sb.grupo.idgrupo "
					+ " AND ma.idmarca = mo.marca.idmarca "
					+ " AND l.idlocal = :pCODIGO ", Object[].class)
					.setParameter("pCODIGO", local.getIdlocal())
					.getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public List<Transferencia> pesquisaTransferenciaMobile() {
		try {
			return em.createQuery("SELECT t FROM Transferencia t ORDER BY t.idtransferencia", Transferencia.class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
