package br.gov.mt.saude.cuiaba.component;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadArquivo")
public class UploadArquivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadArquivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"upload/arquivos/"+request.getParameter("getfile"));
            if (file.exists()) {
                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file));
                response.setContentLength((int) file.length());
                response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            }
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"upload/arquivos/"+ request.getParameter("delfile"));
            if (file.exists()) {
                file.delete(); // TODO:check and report success
            } 
        } 

	}
	
	
    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpg")){
                mimetype = "image/jpg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpeg")){
                mimetype = "image/jpeg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("gif")){
                mimetype = "image/gif";
            }else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }
    
   
    
    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");   
        String filename = "";
        JSONArray json = new JSONArray();
        String json_string = "";
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                	
                    	filename = getMudaNome(item.getName());
                    	
                        File file = new File(request.getServletContext().getRealPath("/")+"upload/arquivos/", filename);
                        
                        item.write(file);
                     
                        JSONObject jsono = new JSONObject();
                                                                                                                   
                        jsono.put("name", filename);
                        jsono.put("old", item.getName());
                        jsono.put("type", FilenameUtils.getExtension(filename));
                        jsono.put("size", item.getSize());                        
                        jsono.put("delete_url", "/UploadArquivo?delfile=" + filename);
                        jsono.put("delete_type", "GET");                        
                        json.put(jsono);                        
                        //jsono_principal.put("files", json.toString());                        
                                                
                        json_string = json.toString();  //jsono_principal.toString();
                        
                        System.out.println(json_string);    
                                            
                }
            }
        } catch (FileUploadException e) {
                throw new RuntimeException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        } finally {
            writer.write(json_string);        	
            writer.close();
        }
	}

}
