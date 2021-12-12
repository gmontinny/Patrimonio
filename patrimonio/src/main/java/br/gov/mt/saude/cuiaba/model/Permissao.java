package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the permissao database table.
 * 
 */
@Entity
@NamedQuery(name="Permissao.findAll", query="SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="permissao_idpermissao", sequenceName = "permissao_idpermissao_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="permissao_idpermissao")
	private Integer idpermissao;

	private Integer deletepermissao;

	private Integer insertpermissao;

	private Integer selectpermissao;

	private Integer updatepermissao;

	//bi-directional many-to-one association to Tabela	
	@ManyToOne
	@JoinColumn(name="idtabela")
	private Tabela tabela;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Permissao() {
	}

	public Integer getIdpermissao() {
		return this.idpermissao;
	}

	public void setIdpermissao(Integer idpermissao) {
		this.idpermissao = idpermissao;
	}

	public Integer getDeletepermissao() {
		return this.deletepermissao;
	}

	public void setDeletepermissao(Integer deletepermissao) {
		this.deletepermissao = deletepermissao;
	}

	public Integer getInsertpermissao() {
		return this.insertpermissao;
	}

	public void setInsertpermissao(Integer insertpermissao) {
		this.insertpermissao = insertpermissao;
	}

	public Integer getSelectpermissao() {
		return this.selectpermissao;
	}

	public void setSelectpermissao(Integer selectpermissao) {
		this.selectpermissao = selectpermissao;
	}

	public Integer getUpdatepermissao() {
		return this.updatepermissao;
	}

	public void setUpdatepermissao(Integer updatepermissao) {
		this.updatepermissao = updatepermissao;
	}

	public Tabela getTabela() {
		return this.tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}