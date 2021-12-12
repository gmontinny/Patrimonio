package br.gov.mt.saude.cuiaba.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the arquivos database table.
 * 
 */
@Entity
@Table(name="arquivos")
@NamedQuery(name="Arquivo.findAll", query="SELECT a FROM Arquivo a ORDER BY a.idarquivos")
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="arquivos_idarquivos", sequenceName = "arquivos_idarquivos_seq")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="arquivos_idarquivos")
	private Integer idarquivos;

	@Temporal(TemporalType.DATE)
	private Date dataarquivos;
		
	private String descarquivos;

	private String file;

	private Time horaaquivos;
		
	private Integer numeroarquivo;

	public Arquivo() {
	}

	public Integer getIdarquivos() {
		return this.idarquivos;
	}

	public void setIdarquivos(Integer idarquivos) {
		this.idarquivos = idarquivos;
	}

	public Date getDataarquivos() {
		return this.dataarquivos;
	}

	public void setDataarquivos(Date dataarquivos) {
		this.dataarquivos = dataarquivos;
	}

	public String getDescarquivos() {
		return this.descarquivos;
	}

	public void setDescarquivos(String descarquivos) {
		this.descarquivos = descarquivos;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Time getHoraaquivos() {
		return this.horaaquivos;
	}

	public void setHoraaquivos(Time horaaquivos) {
		this.horaaquivos = horaaquivos;
	}

	public Integer getNumeroarquivo() {
		return numeroarquivo;
	}

	public void setNumeroarquivo(Integer numeroarquivo) {
		this.numeroarquivo = numeroarquivo;
	}
	
	

}