package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the geraarquivo database table.
 * 
 */
@Entity
@NamedQuery(name="Geraarquivo.findAll", query="SELECT g FROM Geraarquivo g")
public class Geraarquivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="geraarquivo_idgera", sequenceName = "geraarquivo_idgera_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="geraarquivo_idgera")
	private Integer idgera;

	@Temporal(TemporalType.DATE)
	private Date datagera;

	private Time horagera;

	private Integer idusuario;

	private Integer statusgera;

	public Geraarquivo() {
	}

	public Integer getIdgera() {
		return this.idgera;
	}

	public void setIdgera(Integer idgera) {
		this.idgera = idgera;
	}

	public Date getDatagera() {
		return this.datagera;
	}

	public void setDatagera(Date datagera) {
		this.datagera = datagera;
	}

	public Time getHoragera() {
		return this.horagera;
	}

	public void setHoragera(Time horagera) {
		this.horagera = horagera;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getStatusgera() {
		return this.statusgera;
	}

	public void setStatusgera(Integer statusgera) {
		this.statusgera = statusgera;
	}

}