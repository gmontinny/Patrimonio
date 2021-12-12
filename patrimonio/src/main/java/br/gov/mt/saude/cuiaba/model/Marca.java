package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m ORDER BY m.descmarca ")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="marca_idmarca", sequenceName = "marca_idmarca_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="marca_idmarca")
	private Integer idmarca;

	@NotNull(message="DESCRIÇÃO Marca Campo Obrigatório !")
	private String descmarca;

	//bi-directional many-to-one association to Modelo
	@OneToMany(mappedBy="marca")
	private List<Modelo> modelos;

	public Marca() {
	}

	public Integer getIdmarca() {
		return this.idmarca;
	}

	public void setIdmarca(Integer idmarca) {
		this.idmarca = idmarca;
	}

	public String getDescmarca() {
		return this.descmarca;
	}

	public void setDescmarca(String descmarca) {
		this.descmarca = descmarca;
	}

	public List<Modelo> getModelos() {
		return this.modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo addModelo(Modelo modelo) {
		getModelos().add(modelo);
		modelo.setMarca(this);

		return modelo;
	}

	public Modelo removeModelo(Modelo modelo) {
		getModelos().remove(modelo);
		modelo.setMarca(null);

		return modelo;
	}

}