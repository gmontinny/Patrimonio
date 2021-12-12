package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the local database table.
 * 
 */
@Entity
@NamedQuery(name="Local.findAll", query="SELECT l FROM Local l ORDER BY l.desclocal")
public class Local implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="local_idlocal", sequenceName = "local_idlocal_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="local_idlocal")
	private Integer idlocal;

	@NotNull(message="NOME do Local CAMPO Obrigatório !")
	private String desclocal;
	
	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="idsetor")
	private Setor setor;

	public Local() {
	}

	public Integer getIdlocal() {
		return this.idlocal;
	}

	public void setIdlocal(Integer idlocal) {
		this.idlocal = idlocal;
	}

	public String getDesclocal() {
		return this.desclocal;
	}

	public void setDesclocal(String desclocal) {
		this.desclocal = desclocal;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	

}