package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the patrimonio database table.
 * 
 */
@Entity
@NamedQuery(name="Patrimonio.findAll", query="SELECT p FROM Patrimonio p")
public class Patrimonio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idpatrimonio;
	
	private String serialpatrimonio;

	@NotNull(message="SITUAÇÃO do Patrimônio Campo Obrigatório !")
	private Integer situacaopatrimonio;

	private Integer statusbaixa;
	
	private String obspatrimonio;

	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="idmaterial")
	@NotNull(message="MATERIAL Campo Obrigatório !")
	private Material material;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name="idmodelo")
	@NotNull(message="MODELO Campo Obrigatório !")
	private Modelo modelo;
	
	//bi-directional many-to-one association to Fornecedor
	@ManyToOne
	@JoinColumn(name="idfornecedor")
	@NotNull(message="FORNECEDOR Campo Obrigatório !")
	private Fornecedor fornecedor;
	
	//bi-directional many-to-one association to Local
	@ManyToOne
	@JoinColumn(name="idlocal")
	@NotNull(message="LOCAL Campo Obrigatório !")
	private Local local;
	
	private Integer numeroarquivo;
	
	private String imgpatrimonio;


	public Patrimonio() {
	}

	public String getIdpatrimonio() {
		return this.idpatrimonio;
	}

	public void setIdpatrimonio(String idpatrimonio) {
		this.idpatrimonio = idpatrimonio;
	}

	public String getSerialpatrimonio() {
		return this.serialpatrimonio;
	}

	public void setSerialpatrimonio(String serialpatrimonio) {
		this.serialpatrimonio = serialpatrimonio;
	}

	public Integer getSituacaopatrimonio() {
		return this.situacaopatrimonio;
	}

	public void setSituacaopatrimonio(Integer situacaopatrimonio) {
		this.situacaopatrimonio = situacaopatrimonio;
	}

	public Integer getStatusbaixa() {
		return this.statusbaixa;
	}

	public void setStatusbaixa(Integer statusbaixa) {
		this.statusbaixa = statusbaixa;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getObspatrimonio() {
		return obspatrimonio;
	}

	public void setObspatrimonio(String obspatrimonio) {
		this.obspatrimonio = obspatrimonio;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getNumeroarquivo() {
		return numeroarquivo;
	}

	public void setNumeroarquivo(Integer numeroarquivo) {
		this.numeroarquivo = numeroarquivo;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getImgpatrimonio() {
		return imgpatrimonio;
	}

	public void setImgpatrimonio(String imgpatrimonio) {
		this.imgpatrimonio = imgpatrimonio;
	}
	

}