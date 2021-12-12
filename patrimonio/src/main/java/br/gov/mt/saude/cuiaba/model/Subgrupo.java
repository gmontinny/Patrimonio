package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the subgrupo database table.
 * 
 */
@Entity
@NamedQuery(name="Subgrupo.findAll", query="SELECT s FROM Subgrupo s ORDER BY s.descsubgrupo")
public class Subgrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="subgrupo_idsubgrupo", sequenceName = "subgrupo_idsubgrupo_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="subgrupo_idsubgrupo")
	private Integer idsubgrupo;

	@NotNull(message="DESCRIÇÃO do Subgrupo Campo Obrigatório !")
	private String descsubgrupo;

	//bi-directional many-to-one association to Material
	@OneToMany(mappedBy="subgrupo")
	private List<Material> materials;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="idgrupo")
	@NotNull(message="DESCRIÇÃO do Grupo Campo Obrigatório !")
	private Grupo grupo;

	public Subgrupo() {
	}

	public Integer getIdsubgrupo() {
		return this.idsubgrupo;
	}

	public void setIdsubgrupo(Integer idsubgrupo) {
		this.idsubgrupo = idsubgrupo;
	}

	public String getDescsubgrupo() {
		return this.descsubgrupo;
	}

	public void setDescsubgrupo(String descsubgrupo) {
		this.descsubgrupo = descsubgrupo;
	}

	public List<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public Material addMaterial(Material material) {
		getMaterials().add(material);
		material.setSubgrupo(this);

		return material;
	}

	public Material removeMaterial(Material material) {
		getMaterials().remove(material);
		material.setSubgrupo(null);

		return material;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}