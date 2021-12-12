package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the material database table.
 * 
 */
@Entity
@NamedQuery(name="Material.findAll", query="SELECT m FROM Material m ORDER BY m.descmaterial")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="material_idmaterial", sequenceName = "material_idmaterial_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="material_idmaterial")
	private Integer idmaterial;

	@NotNull(message="DESCRIÇÃO Material Campo Obrigatório !")
	private String descmaterial;

	//bi-directional many-to-one association to Subgrupo
	@ManyToOne
	@JoinColumn(name="idsubgrupo")
	@NotNull(message="SUBGRUPO Campo Obrigatório !")
	private Subgrupo subgrupo;

	//bi-directional many-to-one association to Patrimonio
	@OneToMany(mappedBy="material")
	private List<Patrimonio> patrimonios;

	public Material() {
	}

	public Integer getIdmaterial() {
		return this.idmaterial;
	}

	public void setIdmaterial(Integer idmaterial) {
		this.idmaterial = idmaterial;
	}

	public String getDescmaterial() {
		return this.descmaterial;
	}

	public void setDescmaterial(String descmaterial) {
		this.descmaterial = descmaterial;
	}

	public Subgrupo getSubgrupo() {
		return this.subgrupo;
	}

	public void setSubgrupo(Subgrupo subgrupo) {
		this.subgrupo = subgrupo;
	}

	public List<Patrimonio> getPatrimonios() {
		return this.patrimonios;
	}

	public void setPatrimonios(List<Patrimonio> patrimonios) {
		this.patrimonios = patrimonios;
	}

	public Patrimonio addPatrimonio(Patrimonio patrimonio) {
		getPatrimonios().add(patrimonio);
		patrimonio.setMaterial(this);

		return patrimonio;
	}

	public Patrimonio removePatrimonio(Patrimonio patrimonio) {
		getPatrimonios().remove(patrimonio);
		patrimonio.setMaterial(null);

		return patrimonio;
	}

}