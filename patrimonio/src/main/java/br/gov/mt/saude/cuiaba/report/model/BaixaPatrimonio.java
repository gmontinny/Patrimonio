package br.gov.mt.saude.cuiaba.report.model;

public class BaixaPatrimonio {
	private String numeroPatrimonio;
	private String numeroSeria;
	private String nomeSecretaria;
	private String nomeSetor;
	private String nomeLocal;
	private String descGrupo;
	private String descSubgrupo;
	private String descMaterial;
	private String descMarca;
	private String descModelo;
	private String nomeFornecedor;
	
	
	public BaixaPatrimonio(String numeroPatrimonio, String numeroSeria, String nomeSecretaria, String nomeSetor,
			String nomeLocal, String descGrupo, String descSubgrupo, String descMaterial, String descMarca,
			String descModelo, String nomeFornecedor) {
		this.numeroPatrimonio = numeroPatrimonio;
		this.numeroSeria = numeroSeria;
		this.nomeSecretaria = nomeSecretaria;
		this.nomeSetor = nomeSetor;
		this.nomeLocal = nomeLocal;
		this.descGrupo = descGrupo;
		this.descSubgrupo = descSubgrupo;
		this.descMaterial = descMaterial;
		this.descMarca = descMarca;
		this.descModelo = descModelo;
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNumeroPatrimonio() {
		return numeroPatrimonio;
	}

	public String getNumeroSeria() {
		return numeroSeria;
	}

	public String getNomeSecretaria() {
		return nomeSecretaria;
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public String getDescGrupo() {
		return descGrupo;
	}

	public String getDescSubgrupo() {
		return descSubgrupo;
	}

	public String getDescMaterial() {
		return descMaterial;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public String getDescModelo() {
		return descModelo;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	
	
	
	
	
}
