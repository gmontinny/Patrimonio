package br.gov.mt.saude.cuiaba.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
public class ErrorController {
	private final Result result;

	@Inject
	public ErrorController(Result result) {	
		this.result = result;
	}
	
	public ErrorController() {
		this(null);
	}
	
	@Path("/error/error404")
	public void error404() {
		
	}
	
	@Path("/error/error500")
	public void error500() {
		
	}

	
}
