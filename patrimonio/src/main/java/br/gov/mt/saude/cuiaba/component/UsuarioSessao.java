package br.gov.mt.saude.cuiaba.component;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;

import br.gov.mt.saude.cuiaba.model.Usuario;

@SessionScoped
public class UsuarioSessao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public boolean isLogged() {
		return usuario != null;
	}
	
	@PreDestroy
	public void logout(){
		usuario = null;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {		
		this.usuario = usuario;
	}

}
