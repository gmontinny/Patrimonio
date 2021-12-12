package br.gov.mt.saude.cuiaba.mobile.model;

public class TransferenciaMobile {
	private long idtransferencia;;
	private int idsetororigem;;
	private int idsetordestino;;
	private String idpatrimonio;;
	private String datatransferencia;
	private String horatransferencia;
	private int idusuario;
	private int flag;
	public long getIdtransferencia() {
		return idtransferencia;
	}
	public void setIdtransferencia(long idtransferencia) {
		this.idtransferencia = idtransferencia;
	}
	public int getIdsetororigem() {
		return idsetororigem;
	}
	public void setIdsetororigem(int idsetororigem) {
		this.idsetororigem = idsetororigem;
	}
	public int getIdsetordestino() {
		return idsetordestino;
	}
	public void setIdsetordestino(int idsetordestino) {
		this.idsetordestino = idsetordestino;
	}
	public String getIdpatrimonio() {
		return idpatrimonio;
	}
	public void setIdpatrimonio(String idpatrimonio) {
		this.idpatrimonio = idpatrimonio;
	}
	public String getDatatransferencia() {
		return datatransferencia;
	}
	public void setDatatransferencia(String datatransferencia) {
		this.datatransferencia = datatransferencia;
	}
	public String getHoratransferencia() {
		return horatransferencia;
	}
	public void setHoratransferencia(String horatransferencia) {
		this.horatransferencia = horatransferencia;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
