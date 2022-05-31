package com.doks.conferencia.resource;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TesteResource {
	
	 
	
	@RequestMapping(value = "/Lote", method = RequestMethod.GET)
	   public String redirect() {
	      return "redirect:/Lote/index.html";
	   }
	

	
	


}
