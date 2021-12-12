package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the transferencia database table.
 * 
 */
@Entity
@NamedQuery(name="Transferencia.findAll", query="SELECT t FROM Transferencia t")
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="transferencia_idtransferencia", sequenceName = "transferencia_idtransferencia_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="transferencia_idtransferencia")
	private Integer idtransferencia;

	@Temporal(TemporalType.DATE)
	private Date datatransferencia;

	private Time horatransferencia;

	private String idpatrimonio;

	@NotNull(message="Local de Destino Campo Obrigatorio !")
	private Integer idlocaldestino;

	@NotNull(message="Local de Origem Campo Obrigatorio !")
	private Integer idlocalorigem;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Transferencia() {
	}

	public Integer getIdtransferencia() {
		return this.idtransferencia;
	}

	public void setIdtransferencia(Integer idtransferencia) {
		this.idtransferencia = idtransferencia;
	}

	public Date getDatatransferencia() {
		return this.datatransferencia;
	}

	public void setDatatransferencia(Date datatransferencia) {
		this.datatransferencia = datatransferencia;
	}

	public Time getHoratransferencia() {
		return this.horatransferencia;
	}

	public void setHoratransferencia(Time horatransferencia) {
		this.horatransferencia = horatransferencia;
	}

	public String getIdpatrimonio() {
		return this.idpatrimonio;
	}

	public void setIdpatrimonio(String idpatrimonio) {
		this.idpatrimonio = idpatrimonio;
	}

	public Integer getIdlocaldestino() {
		return idlocaldestino;
	}

	public void setIdlocaldestino(Integer idlocaldestino) {
		this.idlocaldestino = idlocaldestino;
	}

	public Integer getIdlocalorigem() {
		return idlocalorigem;
	}

	public void setIdlocalorigem(Integer idlocalorigem) {
		this.idlocalorigem = idlocalorigem;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}