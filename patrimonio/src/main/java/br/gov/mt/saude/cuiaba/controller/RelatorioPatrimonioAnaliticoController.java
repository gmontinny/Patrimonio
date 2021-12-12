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
import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Patrimonio;
import br.gov.mt.saude.cuiaba.report.PatrimonioRelatorioAnaliticoPDF;
import br.gov.mt.saude.cuiaba.report.model.PatrimonioRelatorio;

@Controller
public class RelatorioPatrimonioAnaliticoController {
	private final ServletContext servletContext;
	private final PatrimonioDAOImpl patrimoniodao;
	private final HttpServletRequest request;
	
	@Inject
	public RelatorioPatrimonioAnaliticoController(ServletContext servletContext, PatrimonioDAOImpl patrimoniodao, HttpServletRequest reques) {
		this.servletContext = servletContext;
		this.patrimoniodao = patrimoniodao;
		request = reques;
	}
	
	public RelatorioPatrimonioAnaliticoController() {
		this(null,null,null);
	}
	
	@Path("/relatorioanalitico/pdf")
	public Download pdfReport(String numeropatrimonio) {
		Report report = null;
		report = generateRelatorioAnalitico(numeropatrimonio);
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
	
	private Report generateRelatorioAnalitico(String numeropatrimonio) {
		List<PatrimonioRelatorio> patrimonioRelatorios = new ArrayList<>();
		Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(numeropatrimonio);
		ImageIcon gtoImgPatrimonio = null;
		String situacao = "";
		
		switch (patrimonio.getSituacaopatrimonio()) {
		case 1:
			situacao = "NOVO";
			break;
		case 2:
			situacao = "USADO";
			break;
		case 3:
			situacao = "QUEBRADO";
			break;
		case 4:
			situacao = "DOADO";
			break;
		default:
			break;
		}
		
		
		
		PatrimonioRelatorio patrimonioRelatorio = new PatrimonioRelatorio(patrimonio.getIdpatrimonio(), 
				patrimonio.getSerialpatrimonio(), situacao, patrimonio.getLocal().getSetor().getSecretaria().getNomsecretaria(), 
				patrimonio.getLocal().getSetor().getNomsetor(), patrimonio.getLocal().getDesclocal(), 
				patrimonio.getMaterial().getSubgrupo().getGrupo().getDescgrupo(), patrimonio.getMaterial().getSubgrupo().getDescsubgrupo(),
				patrimonio.getMaterial().getDescmaterial(), patrimonio.getModelo().getMarca().getDescmarca(), 
				patrimonio.getModelo().getDescmodelo(), patrimonio.getFornecedor().getRazaosocial(), patrimonio.getImgpatrimonio(), patrimonio.getObspatrimonio(), patrimonio.getStatusbaixa() == null ? 0 : 1);
		
		patrimonioRelatorios.add(patrimonioRelatorio);
		
		String nomeImagem = getMudaNome("imagem.jpg");
		String path = servletContext.getRealPath("/upload/imagens/"+nomeImagem);
		String semImagem = servletContext.getRealPath("/assets/images/semimagem.jpeg");
		
		if (patrimonio.getImgpatrimonio() != null) {
			new Functions().convertBase64ToImage(path, patrimonio.getImgpatrimonio());
			gtoImgPatrimonio = new ImageIcon(path);
		}else {
			gtoImgPatrimonio = new ImageIcon(semImagem);
			path = null;
		}
		
		String logo = servletContext.getRealPath("/assets/images/relatorio_logo.jpg");
		
				
		Locale localeBR = new Locale("pt", "BR");
		
		String carimbo = request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() + request.getContextPath() + "/assets/images/carimbo.png";
		
		ImageIcon gto = new ImageIcon(logo);
		
		
		
		
		return new PatrimonioRelatorioAnaliticoPDF(patrimonioRelatorios)
				.addParameter("logo",gto.getImage())
				.addParameter("imgMaterial",gtoImgPatrimonio.getImage())
				.addParameter("pbaixa", patrimonio.getStatusbaixa() == null ? 0 : 1)
				.addParameter("imgBaixa", carimbo)
				.addParameter("JRParameter.REPORT_LOCALE", localeBR);
	}
	
}
