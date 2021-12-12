package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import br.com.caelum.stella.bean.validation.CPF;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u ORDER BY u.nomusuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="usuario_idusuario", sequenceName = "usuario_idusuario_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="usuario_idusuario")
	private Integer idusuario;

	@CPF(message="CPF invalido !")
	@NotNull(message="CPF Obrigatório !")
	private String cpfusuario;

	@Temporal(TemporalType.DATE)
	private Date datausuario;

	@Email(message="EMAIL invalido !")
	@NotNull(message="EMAIL Obrigatório !")
	private String emailusuario;

	private String imagemusuario;

	@NotNull(message="NOME Obrigatório !")
	@Size(min = 5 , message="NOME campo minímo 5 caracteres")
	private String nomusuario;

	@NotNull(message="SENHA Obrigatório !")
	@Size(min = 6 , message="SENHA campo minímo 6 caracteres")
	private String senusuario;

	@NotNull(message="SITUAÇÃO Obrigatório !")
	private Integer statususuario;

	@NotNull(message="TIPO USUARIO Obrigatório !")
	private Integer tipousuario;

	//bi-directional many-to-one association to Baixa
	@OneToMany(mappedBy="usuario")
	private List<Baixa> baixas;

	//bi-directional many-to-one association to Permissao
	@OneToMany(mappedBy="usuario")
	private List<Permissao> permissaos;

	//bi-directional many-to-one association to Transferencia
	@OneToMany(mappedBy="usuario")
	private List<Transferencia> transferencias;

	public Usuario() {
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getCpfusuario() {
		return this.cpfusuario;
	}

	public void setCpfusuario(String cpfusuario) {
		this.cpfusuario = cpfusuario;
	}

	public Date getDatausuario() {
		return this.datausuario;
	}

	public void setDatausuario(Date datausuario) {
		this.datausuario = datausuario;
	}

	public String getEmailusuario() {
		return this.emailusuario;
	}

	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}

	public String getImagemusuario() {
		return this.imagemusuario;
	}

	public void setImagemusuario(String imagemusuario) {
		this.imagemusuario = imagemusuario;
	}

	public String getNomusuario() {
		return this.nomusuario;
	}

	public void setNomusuario(String nomusuario) {
		this.nomusuario = nomusuario;
	}

	public String getSenusuario() {
		return this.senusuario;
	}

	public void setSenusuario(String senusuario) {
		this.senusuario = senusuario;
	}

	public Integer getStatususuario() {
		return this.statususuario;
	}

	public void setStatususuario(Integer statususuario) {
		this.statususuario = statususuario;
	}

	public Integer getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(Integer tipousuario) {
		this.tipousuario = tipousuario;
	}

	public List<Baixa> getBaixas() {
		return this.baixas;
	}

	public void setBaixas(List<Baixa> baixas) {
		this.baixas = baixas;
	}

	public Baixa addBaixa(Baixa baixa) {
		getBaixas().add(baixa);
		baixa.setUsuario(this);

		return baixa;
	}

	public Baixa removeBaixa(Baixa baixa) {
		getBaixas().remove(baixa);
		baixa.setUsuario(null);

		return baixa;
	}

	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}

	public Permissao addPermissao(Permissao permissao) {
		getPermissaos().add(permissao);
		permissao.setUsuario(this);

		return permissao;
	}

	public Permissao removePermissao(Permissao permissao) {
		getPermissaos().remove(permissao);
		permissao.setUsuario(null);

		return permissao;
	}

	public List<Transferencia> getTransferencias() {
		return this.transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Transferencia addTransferencia(Transferencia transferencia) {
		getTransferencias().add(transferencia);
		transferencia.setUsuario(this);

		return transferencia;
	}

	public Transferencia removeTransferencia(Transferencia transferencia) {
		getTransferencias().remove(transferencia);
		transferencia.setUsuario(null);

		return transferencia;
	}

}