package br.gov.mt.saude.cuiaba.report.model;

public class PatrimonioRelatorio {
	private String numeropatrimonio;
	private String serial;
	private String situacao;
	private String secretaria;
	private String setor;
	private String local;
	private String grupo;
	private String subgrupo;
	private String material;
	private String marca;
	private String modelo;
	private String fornecedor;
	private String imagem;
	private String observacao;
	private int baixa;

	public PatrimonioRelatorio(String numeropatrimonio, String serial, String situacao, String secretaria, String setor,
			String local, String grupo, String subgrupo, String material, String marca, String modelo,
			String fornecedor, String imagem, String observacao, int baixa) {
		this.numeropatrimonio = numeropatrimonio;
		this.serial = serial;
		this.situacao = situacao;
		this.secretaria = secretaria;
		this.setor = setor;
		this.local = local;
		this.grupo = grupo;
		this.subgrupo = subgrupo;
		this.material = material;
		this.marca = marca;
		this.modelo = modelo;
		this.fornecedor = fornecedor;
		this.imagem = imagem;
		this.observacao = observacao;
		this.baixa = baixa;
	}

	public String getNumeropatrimonio() {
		return numeropatrimonio;
	}

	public String getSerial() {
		return serial;
	}

	public String getSituacao() {
		return situacao;
	}

	public String getSecretaria() {
		return secretaria;
	}

	public String getSetor() {
		return setor;
	}

	public String getLocal() {
		return local;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public String getMaterial() {
		return material;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getImagem() {
		return imagem;
	}

	public int getBaixa() {
		return baixa;
	}

	public String getObservacao() {
		return observacao;
	}
	
	
	
}
