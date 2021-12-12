package br.gov.mt.saude.cuiaba.component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class Functions {
	
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
	
	public Functions() {
	}

	public static int daysBetween(Date before, Date after) {
		return Days.daysBetween(new DateTime(before.getTime()), new DateTime(after.getTime())).getDays();
	}

	public static int daysUntilToday(Date date) {
		return Days.daysBetween(new DateTime(date.getTime()), new DateTime()).getDays();
	}
	
	public static int getDiffHour(String value) {
		Date first = null;
		int hoursBetween = 0;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		
		try {
			first = (Date) format.parse(value);
			hoursBetween = Hours.hoursBetween(new LocalDate(first), new LocalDate()).getHours();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
        return hoursBetween;
    }
	
	public static long subTrairDatas(Date dtini, Date dtfin){
		long diff = dtfin.getTime() - dtini.getTime();		
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}	
	
    public static String diferencaDias(String d1){
    	DateTimeZone timeZone = DateTimeZone.forID( "America/Cuiaba" );
    	Locale locale_ptBR = new Locale( "pt" , "BR" );
    	
    	DateTime d2 = new DateTime();	    	
    	
    	DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/yyyy HH:mm:ss" ).withLocale( locale_ptBR ).withZone( timeZone ); 

    	DateTime dateTimeStart = formatter.parseDateTime( d1 );
    	DateTime dateTimeStop  = d2;
    	Period period = new Period( dateTimeStart, dateTimeStop );

    	PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
    			.appendMonths()
		        .appendSuffix(" mês", " meses")
    		    .appendSeparator(" , ")	    			
    			.appendWeeks()
		        .appendSuffix(" semana", " semanas")
    		    .appendSeparator(" , ") 			
    			.appendDays()
		        .appendSuffix(" dia", " dias")
    		    .appendSeparator(" , ")   			
    			.appendHours()
		        .appendSuffix(" hora", " horas")
    		    .appendSeparator(" e ")    			
    			.appendMinutes()
		        .appendSuffix(" minuto", " minutos")	    		    
    			.toFormatter();
    	
    	String output = periodFormatter.withLocale( locale_ptBR ).print( period );
    	
    	return output;
  	
    }
    
	public static String removeTags(String string) {
	    if (string == null || string.length() == 0) {
	        return string;
	    }

	    Matcher m = REMOVE_TAGS.matcher(string);
	    return m.replaceAll("");
	}
	
	public static String removeHTML(String html){
		return  StringEscapeUtils.unescapeHtml(removeTags(html));
	}
	
	public static String retornaTamanho(String size) {
		
		long tamanho = Long.parseLong(size);
		
	    if(tamanho <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(tamanho)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(tamanho/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
	public static String encodeFileToBase64Binary(String fileName) {
	    File file = new File(fileName);	    
		try {
			byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
			return new String(encoded, StandardCharsets.US_ASCII);
		} catch (IOException e) {			
			System.err.println(e.toString());
			return null;
		}
	}
	
	public static void convertBase64ToImage(String arquivo, String base64String ) {
		String[] strings = base64String.split(",");
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		File file = new File(arquivo);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
	} 
		
    public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
        }
        
        outputStream.flush();
        document.close();
        outputStream.close();
    }	

}
