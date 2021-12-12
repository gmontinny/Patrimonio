package br.gov.mt.saude.cuiaba.report.model;

public class TransferenciaPatrimonio {
	private String numeroPatrimonio;
	private String numeroSeria;
	private String nomeSecretaria;
	private String nomeSetorOrigem;
	private String nomeSetorDestino;
	private String nomeLocalOrigem;
	private String nomeLocalDestino;
	private String descGrupo;
	private String descSubgrupo;
	private String descMaterial;
	private String descMarca;
	private String descModelo;
	private String nomeFornecedor;
	private String nomeUsuario;

	public TransferenciaPatrimonio(String numeroPatrimonio, String numeroSeria, String nomeSecretaria,
			String nomeSetorOrigem, String nomeSetorDestino, String nomeLocalOrigem, String nomeLocalDestino,
			String descGrupo, String descSubgrupo, String descMaterial, String descMarca, String descModelo,
			String nomeFornecedor, String nomeUsuario) {
		this.numeroPatrimonio = numeroPatrimonio;
		this.numeroSeria = numeroSeria;
		this.nomeSecretaria = nomeSecretaria;
		this.nomeSetorOrigem = nomeSetorOrigem;
		this.nomeSetorDestino = nomeSetorDestino;
		this.nomeLocalOrigem = nomeLocalOrigem;
		this.nomeLocalDestino = nomeLocalDestino;
		this.descGrupo = descGrupo;
		this.descSubgrupo = descSubgrupo;
		this.descMaterial = descMaterial;
		this.descMarca = descMarca;
		this.descModelo = descModelo;
		this.nomeFornecedor = nomeFornecedor;
		this.nomeUsuario = nomeUsuario;
	}
	
	

	public String getNomeSetorOrigem() {
		return nomeSetorOrigem;
	}


	public String getNomeSetorDestino() {
		return nomeSetorDestino;
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

	public String getNomeLocalOrigem() {
		return nomeLocalOrigem;
	}

	public String getNomeLocalDestino() {
		return nomeLocalDestino;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	
	
	
	
	
}
