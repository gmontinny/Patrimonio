package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the baixa database table.
 * 
 */
@Entity
@NamedQuery(name="Baixa.findAll", query="SELECT b FROM Baixa b ORDER BY b.idbaixa DESC")
public class Baixa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="baixa_idbaixa", sequenceName = "baixa_idbaixa_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="baixa_idbaixa")
	private Integer idbaixa;

	@Temporal(TemporalType.DATE)
	private Date databaixa;

	private Time horabaixa;
	
	@NotNull(message="PATRIMÔNIO campo Obrigatório !")
	private String idpatrimonio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusario")
	private Usuario usuario;

	public Baixa() {
	}

	public Integer getIdbaixa() {
		return this.idbaixa;
	}

	public void setIdbaixa(Integer idbaixa) {
		this.idbaixa = idbaixa;
	}

	public Date getDatabaixa() {
		return this.databaixa;
	}

	public void setDatabaixa(Date databaixa) {
		this.databaixa = databaixa;
	}

	public Time getHorabaixa() {
		return this.horabaixa;
	}

	public void setHorabaixa(Time horabaixa) {
		this.horabaixa = horabaixa;
	}

	public String getIdpatrimonio() {
		return this.idpatrimonio;
	}

	public void setIdpatrimonio(String idpatrimonio) {
		this.idpatrimonio = idpatrimonio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}