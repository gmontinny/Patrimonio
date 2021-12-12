package br.gov.mt.saude.cuiaba.dao.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.mt.saude.cuiaba.dao.UsuarioDAO;
import br.gov.mt.saude.cuiaba.model.Usuario;

@RequestScoped
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO{

	private final EntityManager em;

	@Inject
	public UsuarioDAOImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public UsuarioDAOImpl() {
		this(null);
	}

	@Override
	public List<Usuario> pesquisaUsuarioGeral() {
		try {
			return em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

	@Override
	public boolean validaUsuarioLogin(String cpf, String senha) {
		try {
			TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.cpfusuario = :pCPF AND u.senusuario = :pSENHA)", Usuario.class);
			q.setParameter("pCPF", cpf);
			q.setParameter("pSENHA", senha);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public boolean validaUsuarioEmail(String email) {
		try {
			TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.emailusuario = :pEMAIL )", Usuario.class);
			q.setParameter("pEMAIL", email);
			return !q.getResultList().isEmpty();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}

	@Override
	public Usuario pesquisaUsuarioPorEmail(String email) {
		try {
			return em.createQuery("SELECT u FROM Usuario u WHERE u.emailusuario = :pEMAIL", Usuario.class)
					.setParameter("pEMAIL", email)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());	
			return null;
		}
	}

	@Override
	public Usuario pesquisaUsuarioPorCPF(String cpf) {
		try {
			return em.createQuery("SELECT u FROM Usuario u WHERE u.cpfusuario = :pCPF", Usuario.class)
					.setParameter("pCPF", cpf)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());	
			return null;
		}
	}

	@Override
	public Usuario pesquisaUsuarioPorCPFESenha(String cpf, String senha) {
		try {
			return em.createQuery("SELECT u FROM Usuario u WHERE u.cpfusuario = :pCPF AND u.senusuario = :pSENHA)", Usuario.class)
					.setParameter("pCPF", cpf)
					.setParameter("pSENHA", senha)
					.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
