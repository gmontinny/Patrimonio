package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the modelo database table.
 * 
 */
@Entity
@NamedQuery(name="Modelo.findAll", query="SELECT m FROM Modelo m ORDER BY m.descmodelo")
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="modelo_idmodelo", sequenceName = "modelo_idmodelo_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="modelo_idmodelo")
	private Integer idmodelo;

	@NotNull(message="DESCRIÇÃO Modelo Campo Obrigatório !")
	private String descmodelo;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="idmarca")
	@NotNull(message="MARCA Campo Obrigatório !")
	private Marca marca;


	public Modelo() {
	}

	public Integer getIdmodelo() {
		return this.idmodelo;
	}

	public void setIdmodelo(Integer idmodelo) {
		this.idmodelo = idmodelo;
	}

	public String getDescmodelo() {
		return this.descmodelo;
	}

	public void setDescmodelo(String descmodelo) {
		this.descmodelo = descmodelo;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}


}