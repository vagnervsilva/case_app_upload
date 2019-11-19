package com.itau.demo.controllers;


import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


import com.itau.demo.models.Arquivos;
import com.itau.demo.models.LogArquivos;
import com.itau.demo.clients.ArquivosClient;
import com.itau.demo.clients.LogArquivosClient;


@Controller
public class DetalhesController {
	
	private String nomeArquivo; 
	
/*	@RequestMapping(value = "/detail/{arquivo:.+}", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable String arquivo) {
		this.nomeArquivo = arquivo;
		ArquivosClient clientApiArquivos = new ArquivosClient(); 
		Arquivos detalhes = clientApiArquivos.getArquivosPorNome(arquivo);
	    return new ModelAndView("detalhes", "arquivo", detalhes);

	}	
	
	@RequestMapping(value = "/log/list", method = RequestMethod.GET)
	public String listaLogs(Model model) {
		
		LogArquivosClient clientLogs = new LogArquivosClient();
		
		List<LogArquivos> registroLogs = clientLogs.getLogArquivosPorNome(this.nomeArquivo);
		
		model.addAttribute("listaLogs", registroLogs);
		return "lista_logs :: urlFileList";
	}	*/

}
