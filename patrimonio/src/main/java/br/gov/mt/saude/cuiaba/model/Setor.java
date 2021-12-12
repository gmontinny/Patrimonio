package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the setor database table.
 * 
 */
@Entity
@NamedQuery(name="Setor.findAll", query="SELECT s FROM Setor s ORDER BY s.nomsetor")
public class Setor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="setor_idsetor", sequenceName = "setor_idsetor_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="setor_idsetor")
	private Integer idsetor;

	@NotNull(message="NOME Setor CAMPO Obrigatório !")
	private String nomsetor;

	//bi-directional many-to-one association to Secretaria
	@ManyToOne
	@JoinColumn(name="idsecretaria")
	@NotNull(message="NOME Secretaria CAMPO Obrigatório !")
	private Secretaria secretaria;

	public Setor() {
	}

	public Integer getIdsetor() {
		return this.idsetor;
	}

	public void setIdsetor(Integer idsetor) {
		this.idsetor = idsetor;
	}

	public String getNomsetor() {
		return this.nomsetor;
	}

	public void setNomsetor(String nomsetor) {
		this.nomsetor = nomsetor;
	}

	public Secretaria getSecretaria() {
		return this.secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

}