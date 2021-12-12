package br.gov.mt.saude.cuiaba.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.ArquivoDAOImpl;
import br.gov.mt.saude.cuiaba.model.Arquivo;

@Controller
public class ArquivoController {
	private final Result result;
	private final ArquivoDAOImpl arquivodao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final ServletContext servletContext;
	
	@Inject
	public ArquivoController(Result result, ArquivoDAOImpl arquivodao, UsuarioSessao sessao, Validacao validacao,
			Validator validation, ServletContext servletContext) {
		this.result = result;
		this.arquivodao = arquivodao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.servletContext = servletContext;
	}
	
	public ArquivoController() {
		this(null,null,null,null,null,null);
	}
	
	@Post("/arquivo/salvar")
	public void salvar(@Valid Arquivo arquivo) {
		if(arquivo.getIdarquivos() == null) {
			validacao.valida(1,"arquivo").onErrorForwardTo(AdministrativoController.class).error();
			arquivo.setDataarquivos(new Date());
			arquivo.setHoraaquivos(new java.sql.Time(new Date().getTime()));
			arquivodao.save(arquivo);
		}else {
			validacao.valida(2,"arquivo").onErrorForwardTo(AdministrativoController.class).error();
			arquivo.setDataarquivos(new Date());
			arquivo.setHoraaquivos(new java.sql.Time(new Date().getTime()));
			arquivodao.update(arquivo);
		}
		
		result.nothing();
	}
	
	@Path("/arquivo/listaArquivo")
	public void listaArquivo(int numero) {
		List<Arquivo> arquivos = arquivodao.pesquisaArquivoPorNumero(numero);
		List<Map<String, Object>> dados = new ArrayList<>();
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hr = new SimpleDateFormat("HH:mm");
		
		arquivos
		.stream()
		.filter(a -> a != null)
		.forEach(a -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idarquivos", a.getIdarquivos());
			resultado.put("descarquivos", a.getDescarquivos());
			resultado.put("file", a.getFile());
			resultado.put("dataarquivos", dt.format(a.getDataarquivos()));
			resultado.put("horaaquivos", hr.format(a.getHoraaquivos()));
			resultado.put("numeroarquivo", a.getNumeroarquivo());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
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
        }else if (ext.equalsIgnoreCase("doc")){
        	nome = formatar.format(data)+".doc";
        }else if (ext.equalsIgnoreCase("docx")){
        	nome = formatar.format(data)+".docx";
        }else if (ext.equalsIgnoreCase("pdf")){
         nome = formatar.format(data)+".pdf";
        } 
        
        return nome;
        
   }
    
	private static void mergePdf(List<InputStream> list, OutputStream outputStream) throws DocumentException, IOException
	{
		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
		document.open();
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

		for (InputStream inputStream : list)
		{
			PdfReader pdfReader = new PdfReader(inputStream);
			for (int i = 1; i <= pdfReader.getNumberOfPages(); i++)
			{
				document.newPage();
				PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
				pdfContentByte.addTemplate(page, 0, 0);
			}
		}

		outputStream.flush();
		document.close();
		outputStream.close();
	}    
	
	@Path("/arquivo/mergeArquivo")
	public void mergeArquivo(int numero) {
		List<Arquivo> arquivos = arquivodao.pesquisaArquivoPorNumero(numero);
		List<InputStream> list = new ArrayList<InputStream>();
		OutputStream outputStream;
		String nomeArquivo = getMudaNome("Merger.pdf");
		boolean resultado = false;
		
		arquivos
		.stream()
		.filter(a -> a != null)
		.forEach(a -> {
            try {
            	
            	String file = servletContext.getRealPath("/upload/arquivos/"+a.getFile());            	
				InputStream inputStream = new FileInputStream(new File(file));
				list.add(inputStream); 
				arquivodao.delete(a);
								
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		if(!arquivos.isEmpty()) {
			try {
				String novoArquivo = servletContext.getRealPath("/upload/arquivos/"+nomeArquivo);
				outputStream = new FileOutputStream(new File(novoArquivo));
				mergePdf(list, outputStream);
				resultado = true;
				Arquivo arquivo = new Arquivo();
				arquivo.setDataarquivos(new Date());
				arquivo.setHoraaquivos(new java.sql.Time(new Date().getTime()));
				arquivo.setFile(nomeArquivo);
				arquivo.setNumeroarquivo(numero);
				arquivodao.save(arquivo);
			} catch (FileNotFoundException e) {
				resultado = false;
				e.printStackTrace();
			} catch (DocumentException e) {
				resultado = false;
				e.printStackTrace();
			} catch (IOException e) {
				resultado = false;
				e.printStackTrace();
			}
		}
		
		Map<String, Object> dados = new HashMap<>();
		dados.put("file", nomeArquivo);
		dados.put("resultado", resultado);
		result.use(Results.json()).withoutRoot().from(dados).serialize();

	}
	
	@Path("/arquivo/editar")
	public void editar(int codigo) {
		Arquivo arquivo = arquivodao.getById(Arquivo.class, codigo);
		Map<String, Object> dados = new HashMap<>();
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hr = new SimpleDateFormat("HH:mm");
		
		dados.put("idarquivos", arquivo.getIdarquivos());
		dados.put("descarquivos", arquivo.getDescarquivos());
		dados.put("file", arquivo.getFile());
		dados.put("dataarquivos", dt.format(arquivo.getDataarquivos()));
		dados.put("horaaquivos", hr.format(arquivo.getHoraaquivos()));
		dados.put("numeroarquivo", arquivo.getNumeroarquivo());

		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/arquivo/deletar")
	public void deletar(int codigo) {
		validacao.valida(3,"arquivo").onErrorForwardTo(AdministrativoController.class).error();
		Arquivo arquivo = arquivodao.getById(Arquivo.class, codigo);
		arquivodao.delete(arquivo);
		result.nothing();
	}
	
	
}

