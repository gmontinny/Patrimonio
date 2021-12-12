package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the secretaria database table.
 * 
 */
@Entity
@NamedQuery(name="Secretaria.findAll", query="SELECT s FROM Secretaria s ORDER BY s.nomsecretaria")
public class Secretaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="secretaria_idsecretaria", sequenceName = "secretaria_idsecretaria_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="secretaria_idsecretaria")
	private Integer idsecretaria;

	@NotNull(message="NOME secretaria CAMPO Obrigatório !")
	private String nomsecretaria;

	//bi-directional many-to-one association to Setor
	@OneToMany(mappedBy="secretaria")
	private List<Setor> setors;

	public Secretaria() {
	}

	public Integer getIdsecretaria() {
		return this.idsecretaria;
	}

	public void setIdsecretaria(Integer idsecretaria) {
		this.idsecretaria = idsecretaria;
	}

	public String getNomsecretaria() {
		return this.nomsecretaria;
	}

	public void setNomsecretaria(String nomsecretaria) {
		this.nomsecretaria = nomsecretaria;
	}

	public List<Setor> getSetors() {
		return this.setors;
	}

	public void setSetors(List<Setor> setors) {
		this.setors = setors;
	}

	public Setor addSetor(Setor setor) {
		getSetors().add(setor);
		setor.setSecretaria(this);

		return setor;
	}

	public Setor removeSetor(Setor setor) {
		getSetors().remove(setor);
		setor.setSecretaria(null);

		return setor;
	}

}