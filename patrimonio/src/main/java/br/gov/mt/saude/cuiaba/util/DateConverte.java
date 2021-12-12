package br.gov.mt.saude.cuiaba.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;

@Convert(Date.class)
@ApplicationScoped
public class DateConverte implements Converter<Date>{

	@Override
	public Date convert(String value, Class<? extends Date> type) {
		
		if (value == null || value.equals("")) { 
			return null;
		}
		
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
        format.setLenient(false);
        
        try {
        	
        	System.out.println("DATA :" + value);
        	
			return (Date) format.parse(value);
			
		} catch (ParseException e) {
			throw new ConversionException(new ConversionMessage(
                    "data", "Data invalida: " + value));			
		}       
		
		
		
	}

}
