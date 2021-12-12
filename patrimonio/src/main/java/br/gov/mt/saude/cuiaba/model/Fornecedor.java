package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import br.com.caelum.stella.bean.validation.CNPJ;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f ORDER BY f.razaosocial ")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="fornecedor_idfornecedor", sequenceName = "fornecedor_idfornecedor_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="fornecedor_idfornecedor")
	private Integer idfornecedor;

	@NotNull(message="BAIRRO Campo Obrigatório !")
	private String bairrofornecedor;

	private String celularfornecedor;

	@CNPJ(message="CNPJ Invalido !")
	@NotNull(message="CNPJ Campo Obrigatório !")
	private String cnpjfornecedor;

	private String contatofornecedor;

	@Email(message="Email Invalido !")
	@NotNull(message="EMAIL Campo Obrigatório !")
	private String emailfornecedor;

	@NotNull(message="ENDEREÇO Campo Obrigatório !")
	private String enderecofornecedor;

	@NotNull(message="NOME FANTASIA Campo Obrigatório !")
	private String nomefantasia;

	@NotNull(message="RAZÃO SOCIAL Campo Obrigatório !")
	private String razaosocial;

	private String telefonefornecedor;

	public Fornecedor() {
	}

	public Integer getIdfornecedor() {
		return this.idfornecedor;
	}

	public void setIdfornecedor(Integer idfornecedor) {
		this.idfornecedor = idfornecedor;
	}

	public String getBairrofornecedor() {
		return this.bairrofornecedor;
	}

	public void setBairrofornecedor(String bairrofornecedor) {
		this.bairrofornecedor = bairrofornecedor;
	}

	public String getCelularfornecedor() {
		return this.celularfornecedor;
	}

	public void setCelularfornecedor(String celularfornecedor) {
		this.celularfornecedor = celularfornecedor;
	}

	public String getCnpjfornecedor() {
		return this.cnpjfornecedor;
	}

	public void setCnpjfornecedor(String cnpjfornecedor) {
		this.cnpjfornecedor = cnpjfornecedor;
	}

	public String getContatofornecedor() {
		return this.contatofornecedor;
	}

	public void setContatofornecedor(String contatofornecedor) {
		this.contatofornecedor = contatofornecedor;
	}

	public String getEmailfornecedor() {
		return this.emailfornecedor;
	}

	public void setEmailfornecedor(String emailfornecedor) {
		this.emailfornecedor = emailfornecedor;
	}

	public String getEnderecofornecedor() {
		return this.enderecofornecedor;
	}

	public void setEnderecofornecedor(String enderecofornecedor) {
		this.enderecofornecedor = enderecofornecedor;
	}

	public String getNomefantasia() {
		return this.nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	public String getRazaosocial() {
		return this.razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getTelefonefornecedor() {
		return this.telefonefornecedor;
	}

	public void setTelefonefornecedor(String telefonefornecedor) {
		this.telefonefornecedor = telefonefornecedor;
	}

}