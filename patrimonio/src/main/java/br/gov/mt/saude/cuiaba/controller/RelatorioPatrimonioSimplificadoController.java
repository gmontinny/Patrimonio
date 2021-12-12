package br.gov.mt.saude.cuiaba.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.com.caelum.vraptor.observer.download.Download;
import br.gov.mt.saude.cuiaba.component.Functions;
import br.gov.mt.saude.cuiaba.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.SecretariaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.SetorDAOImpl;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Patrimonio;
import br.gov.mt.saude.cuiaba.model.Secretaria;
import br.gov.mt.saude.cuiaba.model.Setor;
import br.gov.mt.saude.cuiaba.report.PatrimonioRelatorioAnaliticoPDF;
import br.gov.mt.saude.cuiaba.report.PatrimonioRelatorioSimplificadoPDF;
import br.gov.mt.saude.cuiaba.report.PatrimonioRelatorioSinteticoPDF;
import br.gov.mt.saude.cuiaba.report.model.PatrimonioRelatorio;

@Controller
public class RelatorioPatrimonioSimplificadoController {
	private final ServletContext servletContext;
	private final PatrimonioDAOImpl patrimoniodao;
	private final SecretariaDAOImpl secretariadao;
	private final SetorDAOImpl setordao;
	private final LocalDAOImpl localdao;
	private final HttpServletRequest request;
	private String situacaoresultado = "";	
	
	@Inject
	public RelatorioPatrimonioSimplificadoController(ServletContext servletContext, PatrimonioDAOImpl patrimoniodao, 
			SecretariaDAOImpl secretariadao, SetorDAOImpl setordao, LocalDAOImpl localdao, HttpServletRequest reques) {
		this.servletContext = servletContext;
		this.patrimoniodao = patrimoniodao;
		this.secretariadao = secretariadao;
		this.setordao = setordao;
		this.localdao = localdao;
		request = reques;
	}
	
	public RelatorioPatrimonioSimplificadoController() {
		this(null,null,null,null,null,null);
	}
	
	@Path("/relatoriosimplificado/pdf")
	public Download pdfReport(String numeropatrimonio, String numeroserial, int situacao, int secretaria, int setor, int local,
			int grupo, int subgrupo, int material, int marca, int modelo, int fornecedor) {
		Report report = null;
		report = generateRelatorioSimplificado(numeropatrimonio, numeroserial, situacao, secretaria, setor, local,
				grupo, subgrupo, material,marca, modelo, fornecedor);
		return new ReportDownload(report, ExportFormats.pdf());
	}

	
    protected String getMudaNome(String arquivo){
    	
 	   Date data = new Date();   	   
        String nome  = "";

        Format formatar = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");             
        
        String ext = FilenameUtils.getExtension(arquivo);
        
        if (ext.equalsIgnoreCase("png")){
        	nome = formatar.format(data)+".png";
        }else if (ext.equalsIgnoreCase("jpg")){
        	nome = formatar.format(data)+".jpg";
        }else if (ext.equalsIgnoreCase("jpeg")){
        	nome = formatar.format(data)+".jpeg";
        }else if (ext.equalsIgnoreCase("gif")){
        	nome = formatar.format(data)+".gif";
        } 
        
        return nome;
        
   }
	
	private Report generateRelatorioSimplificado(String numeropatrimonio, String numeroserial, int situacao, int secretaria, int setor, int local,
			int grupo, int subgrupo, int material, int marca, int modelo, int fornecedor) {
		
		List<PatrimonioRelatorio> patrimonioRelatorios = new ArrayList<>();
		List<Patrimonio> patrimonios = patrimoniodao.pesquisaPatrimonioGeral(secretaria,setor, local, grupo, subgrupo, material, marca, modelo, fornecedor, situacao, null, numeropatrimonio, numeroserial);
		
		String nomeSecretaria = "";
		String nomeSetor = "";
		String nomeLocal = "";
		
		if(secretaria != 0) {
			nomeSecretaria = secretariadao.getById(Secretaria.class, secretaria).getNomsecretaria();
		}
		
		if(setor != 0) {
			nomeSetor = setordao.getById(Setor.class, setor).getNomsetor();
		}
		
		if(local != 0) {
			nomeLocal = localdao.getById(Local.class, local).getDesclocal(); 
		}
		
		patrimonios
		.stream()
		.filter(patrimonio -> patrimonio != null)
		.forEach(patrimonio ->{

			switch (patrimonio.getSituacaopatrimonio()) {
			case 1:
				situacaoresultado = "NOVO";
				break;
			case 2:
				situacaoresultado = "USADO";
				break;
			case 3:
				situacaoresultado = "QUEBRADO";
				break;
			case 4:
				situacaoresultado = "DOADO";
				break;
			default:
				break;
			}
			
			PatrimonioRelatorio patrimonioRelatorio = new PatrimonioRelatorio(patrimonio.getIdpatrimonio(), patrimonio.getSerialpatrimonio(),
					situacaoresultado, patrimonio.getLocal().getSetor().getSecretaria().getNomsecretaria(), patrimonio.getLocal().getSetor().getNomsetor(), 
					patrimonio.getLocal().getDesclocal(), patrimonio.getMaterial().getSubgrupo().getGrupo().getDescgrupo(), patrimonio.getMaterial().getSubgrupo().getDescsubgrupo(),
					patrimonio.getMaterial().getDescmaterial(), patrimonio.getModelo().getMarca().getDescmarca(), patrimonio.getModelo().getDescmodelo(), 
					patrimonio.getFornecedor().getRazaosocial(), null, null, 0);
			patrimonioRelatorios.add(patrimonioRelatorio);
			
		});
		
		
		String logo = servletContext.getRealPath("/assets/images/relatorio_logo.jpg");
				
		Locale localeBR = new Locale("pt", "BR");
		ImageIcon gto = new ImageIcon(logo);
		
		return new PatrimonioRelatorioSimplificadoPDF(patrimonioRelatorios)
				.addParameter("logo",gto.getImage())
				.addParameter("pSecretaria",nomeSecretaria)
				.addParameter("pSetor",nomeSetor)
				.addParameter("pLocal",nomeLocal)
				.addParameter("JRParameter.REPORT_LOCALE", localeBR);
	}
	
}
