package com.itau.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itau.demo.models.Arquivos;
import com.itau.demo.models.HRefModel;
import com.itau.demo.models.Logins;
import com.itau.demo.models.LogArquivos;
import com.itau.demo.clients.ArquivosClient;
import com.itau.demo.clients.LoginsClient;
import com.itau.demo.clients.LogArquivosClient;
import com.itau.demo.util.FileSystemStorageService;


@Controller
public class UploadController {
	
	@Autowired
	private FileSystemStorageService storageService;
	private String nomeArquivo;	
	
	private Logins usuario;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView upload(@AuthenticationPrincipal OidcUser oidcUser) {
		

		usuario = new Logins(ObjectId.get(), 
				oidcUser.getFullName(), 
				oidcUser.getEmail(), 
				oidcUser.getIdToken().getTokenValue(),
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
	    
	    
		LoginsClient clientApiLogin = new LoginsClient(usuario.getToken()); 
	    clientApiLogin.createLogin(usuario);
	    return new ModelAndView("upload", "usuario", usuario);

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String registraAcesso(Model model) {
		return usuario.getNome();
	}
	
	
	@RequestMapping(value = "/files/list", method = RequestMethod.GET)
	public String listFiles(Model model) {
		List<Path> lodf = new ArrayList<>();
		List<HRefModel> uris = new ArrayList<>();

		
		try {
			lodf = storageService.listSourceFiles(storageService.getUploadLocation());
			for(Path pt : lodf) {
				HRefModel href = new HRefModel();
				href.setHref(MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "serveFile", pt.getFileName().toString())
						.build()
						.toString());
				
				href.setHrefText(pt.getFileName().toString() + " - " + storageService.readableFileSize(Files.size(pt)));
				uris.add(href);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("listOfEntries", uris);
		return "file_list :: urlFileList";
	}
		
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		registraLogArquivo(file.getFilename(), "Download", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@RequestMapping(value = "/files/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("descricao") String descricao, RedirectAttributes redirectAttributes) {
		if (file.getSize() > (10 * 1024 * 1024)) {
			redirectAttributes.addFlashAttribute("message", "Arquivo maior que 10MB proibido! ");
			
		} else {
			storageService.store(file);
			redirectAttributes.addFlashAttribute("message", "Upload concluido. Arquivo: " + file.getOriginalFilename() + "!");
			registraArquivoUpload(file.getOriginalFilename(), "Upload", descricao);			
		    
		}
								
		return "redirect:/";
	}
	
    @RequestMapping("/attributes")
    @ResponseBody
    public String attributes(@AuthenticationPrincipal OidcUser oidcUser) {
        return oidcUser.getAttributes().toString();
    }

    @RequestMapping("/authorities")
    @ResponseBody
    public String authorities(@AuthenticationPrincipal OidcUser oidcUser) {
        return oidcUser.getAuthorities().toString();
    }
    
	private void registraArquivoUpload(String nomeArquivo, String acao, String descricao) {
		
		String dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
				
		Arquivos arquivo = new Arquivos(ObjectId.get(),
				nomeArquivo, 
				descricao, 
				usuario.getNome(),
				dataHora);
		
		ArquivosClient clientApiArquivos = new ArquivosClient(usuario.getToken()); 
	    clientApiArquivos.createArquivos(arquivo);
	    registraLogArquivo(nomeArquivo, acao, dataHora);

		
	}
	
	private void registraLogArquivo(String nomeArquivo, String acao, String dataHora) {
	    LogArquivos log = new LogArquivos(ObjectId.get(),
				nomeArquivo, 
				acao, 
				usuario.getNome(),
				dataHora);

	    LogArquivosClient clientApiLogArquivos = new LogArquivosClient();
	    clientApiLogArquivos.createLogArquivos(log);		
		
	}
	
	@RequestMapping(value = "/files/log/list", method = RequestMethod.GET)
	public String listaLogs(Model model) {
		
		LogArquivosClient clientLogs = new LogArquivosClient();
		
		List<LogArquivos> registroLogs = clientLogs.getLogArquivosPorNome(this.nomeArquivo);
		
		model.addAttribute("listaLogs", registroLogs);
		return "lista_logs :: urlLogList";
	}	
	

	

}
