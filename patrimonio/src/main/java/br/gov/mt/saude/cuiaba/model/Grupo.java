package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g ORDER BY g.descgrupo")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="grupo_idgrupo", sequenceName = "grupo_idgrupo_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="grupo_idgrupo")
	private Integer idgrupo;

	@NotNull(message="DESCRIÇÃO do Grupo Campo Obrigatório !")
	private String descgrupo;

	//bi-directional many-to-one association to Subgrupo
	@OneToMany(mappedBy="grupo")
	private List<Subgrupo> subgrupos;

	public Grupo() {
	}

	public Integer getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}

	public String getDescgrupo() {
		return this.descgrupo;
	}

	public void setDescgrupo(String descgrupo) {
		this.descgrupo = descgrupo;
	}

	public List<Subgrupo> getSubgrupos() {
		return this.subgrupos;
	}

	public void setSubgrupos(List<Subgrupo> subgrupos) {
		this.subgrupos = subgrupos;
	}

	public Subgrupo addSubgrupo(Subgrupo subgrupo) {
		getSubgrupos().add(subgrupo);
		subgrupo.setGrupo(this);

		return subgrupo;
	}

	public Subgrupo removeSubgrupo(Subgrupo subgrupo) {
		getSubgrupos().remove(subgrupo);
		subgrupo.setGrupo(null);

		return subgrupo;
	}

}