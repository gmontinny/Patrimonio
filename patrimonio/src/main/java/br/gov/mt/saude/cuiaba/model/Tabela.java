package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the tabela database table.
 * 
 */
@Entity
@NamedQuery(name="Tabela.findAll", query="SELECT t FROM Tabela t ORDER BY t.desctabela")
public class Tabela implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="tabela_idtabela", sequenceName = "tabela_idtabela_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="tabela_idtabela")
	private Integer idtabela;
	
	@NotNull(message="DESCRIÇÃO TABELA Obrigatório !")
	private String desctabela;

	@NotNull(message="NOME TABELA Obrigatório !")
	private String nomtabela;

	@NotNull(message="ORDEM TABELA Obrigatório !")
	private Integer ordtabela;

	@NotNull(message="TIPO TABELA Obrigatório !")
	private Integer tipotabela;
	
	@NotNull(message="ICON TABELA Obrigatório !")
	private String icontabela;
	
	@NotNull(message="URL TABELA Obrigatório !")
	private String urltabela;

	//bi-directional many-to-one association to Permissao
	@OneToMany(mappedBy="tabela")
	private List<Permissao> permissaos;

	public Tabela() {
	}

	public Integer getIdtabela() {
		return this.idtabela;
	}

	public void setIdtabela(Integer idtabela) {
		this.idtabela = idtabela;
	}

	public String getDesctabela() {
		return this.desctabela;
	}

	public void setDesctabela(String desctabela) {
		this.desctabela = desctabela;
	}

	public String getNomtabela() {
		return this.nomtabela;
	}

	public void setNomtabela(String nomtabela) {
		this.nomtabela = nomtabela;
	}

	public Integer getOrdtabela() {
		return this.ordtabela;
	}

	public void setOrdtabela(Integer ordtabela) {
		this.ordtabela = ordtabela;
	}

	public Integer getTipotabela() {
		return this.tipotabela;
	}

	public void setTipotabela(Integer tipotabela) {
		this.tipotabela = tipotabela;
	}

	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}

	public Permissao addPermissao(Permissao permissao) {
		getPermissaos().add(permissao);
		permissao.setTabela(this);

		return permissao;
	}

	public Permissao removePermissao(Permissao permissao) {
		getPermissaos().remove(permissao);
		permissao.setTabela(null);

		return permissao;
	}

	public String getIcontabela() {
		return icontabela;
	}

	public void setIcontabela(String icontabela) {
		this.icontabela = icontabela;
	}

	public String getUrltabela() {
		return urltabela;
	}

	public void setUrltabela(String urltabela) {
		this.urltabela = urltabela;
	}
	
	
	

}