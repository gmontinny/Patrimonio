package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.PermissaoDAO;
import br.gov.mt.saude.cuiaba.model.Permissao;
import br.gov.mt.saude.cuiaba.model.Tabela;
import br.gov.mt.saude.cuiaba.model.Usuario;


@RequestScoped
public class PermissaoDAOImpl extends GenericDAOImpl<Permissao, Integer> implements PermissaoDAO{

	private final EntityManager em;
	
	@Inject
	public PermissaoDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public PermissaoDAOImpl() {
		this(null);
	}

	@Override
	public boolean validaSelect(Usuario usuario, Tabela tabela) {
		TypedQuery<Permissao> q = em.createQuery("SELECT p FROM Permissao p JOIN p.usuario u JOIN p.tabela t WHERE p.selectpermissao = 1 "
				+ "AND p.usuario.idusuario = :idusuario AND p.tabela.idtabela = :idtabela",Permissao.class);
		q.setParameter("idusuario", usuario.getIdusuario());
		q.setParameter("idtabela", tabela.getIdtabela());
		
		return (boolean) !q.getResultList().isEmpty();
	}

	@Override
	public boolean validaInsert(Usuario usuario, Tabela tabela) {
		TypedQuery<Permissao> q = em.createQuery("SELECT p FROM Permissao p JOIN p.usuario u JOIN p.tabela t WHERE p.insertpermissao = 1 "
				+ "AND p.usuario.idusuario = :idusuario AND p.tabela.idtabela = :idtabela",Permissao.class);
		q.setParameter("idusuario", usuario.getIdusuario());
		q.setParameter("idtabela", tabela.getIdtabela());
		
		return (boolean) !q.getResultList().isEmpty();
	}

	@Override
	public boolean validaUpdate(Usuario usuario, Tabela tabela) {
		TypedQuery<Permissao> q = em.createQuery("SELECT p FROM Permissao p JOIN p.usuario u JOIN p.tabela t WHERE p.updatepermissao = 1 "
				+ "AND p.usuario.idusuario = :idusuario AND p.tabela.idtabela = :idtabela",Permissao.class);
		q.setParameter("idusuario", usuario.getIdusuario());
		q.setParameter("idtabela", tabela.getIdtabela());
		
		return (boolean) !q.getResultList().isEmpty();
	}

	@Override
	public boolean validaDelete(Usuario usuario, Tabela tabela) {
		TypedQuery<Permissao> q = em.createQuery("SELECT p FROM Permissao p JOIN p.usuario u JOIN p.tabela t WHERE p.deletepermissao = 1 "
				+ "AND p.usuario.idusuario = :idusuario AND p.tabela.idtabela = :idtabela",Permissao.class);
		q.setParameter("idusuario", usuario.getIdusuario());
		q.setParameter("idtabela", tabela.getIdtabela());

		return (boolean) !q.getResultList().isEmpty();
	}

	@Override
	public List<Permissao> pesquisaPermissaoOrdenadaPorNome(Usuario usuario) {
		return em.createQuery("SELECT p FROM Permissao p JOIN p.usuario u JOIN p.tabela t WHERE p.usuario.idusuario = :idusuario AND t.tipotabela <> -1 ORDER BY t.ordtabela ", Permissao.class)
				.setParameter("idusuario", usuario.getIdusuario())
				.getResultList();
	}

	@Override
	public List<Permissao> pesquisaPermissaoGeral() {
		return em.createNamedQuery("Permissao.findAll",Permissao.class)				
				.getResultList();
	}

	@Override
	public List<Object[]> pesquisaPermissaoPorUsuario(int codigo, int idusuario) {
		try{
			
		List<Object[]> objects =  em
				.createQuery(
				"SELECT t.idtabela,t.desctabela, t.ordtabela ,"
				+ " CASE "
				+ " WHEN (SELECT p.selectpermissao FROM Permissao p WHERE p.tabela.idtabela = t.idtabela AND p.usuario.idusuario = :idusuario) = 1 THEN 1 ELSE 0 "		
				+ " END,  "	
				+ " CASE "
				+ " WHEN (SELECT p.insertpermissao FROM Permissao p WHERE p.tabela.idtabela = t.idtabela AND p.usuario.idusuario = :idusuario) = 1 THEN 1 ELSE 0 "		
				+ " END,  "	
				+ " CASE "
				+ " WHEN (SELECT p.updatepermissao FROM Permissao p WHERE p.tabela.idtabela = t.idtabela AND p.usuario.idusuario = :idusuario) = 1 THEN 1 ELSE 0 "		
				+ " END,  "
				+ " CASE "
				+ " WHEN (SELECT p.deletepermissao FROM Permissao p WHERE p.tabela.idtabela = t.idtabela AND p.usuario.idusuario = :idusuario) = 1 THEN 1 ELSE 0 "		
				+ " END  "					
				+ "FROM Tabela t WHERE t.tipotabela IN (:codigo, -1) ORDER BY t.nomtabela", Object[].class)
				.setParameter("codigo", codigo)
				.setParameter("idusuario", idusuario)
				.getResultList();
		
			return objects;
		
		}catch(RuntimeException ex){
			System.err.println(ex.toString());
			return null;
		}
	}

	@Override
	public int removePermissao(int codigo) {
		try {			
			
			String query = "DELETE FROM Permissao p " +			
			"WHERE p.usuario.idusuario = :codigo ";

			
			int rowDelete = em.createQuery(query)
					.setParameter("codigo", codigo)
					.executeUpdate();
			
			return rowDelete;
			
		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return 0;
		}
	}

}
