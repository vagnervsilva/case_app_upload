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
import com.itau.demo.clients.ArquivosClient;
import com.itau.demo.clients.LoginsClient;
import com.itau.demo.util.FileSystemStorageService;

@Controller
public class UploadController {
	
	@Autowired
	private FileSystemStorageService storageService;
	
	
	private Logins usuario;
	private Arquivos arquivo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView upload(@AuthenticationPrincipal OidcUser oidcUser) {
		
      //  OAuth2AuthorizedClient authorizedClient =
       //         this.authorizedClientService.loadAuthorizedClient("okta", oidcUser.getName());

   //         OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
		
		usuario = new Logins(ObjectId.get(), 
				oidcUser.getFullName(), 
				oidcUser.getEmail(), 
				oidcUser.getIdToken().getTokenValue(),
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
	    
		LoginsClient clientApiLogin = new LoginsClient(usuario.getToken()); 
	    Logins login = clientApiLogin.createLogin(usuario);
	    return new ModelAndView("upload", "usuario", login);

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
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@RequestMapping(value = "/files/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.getSize() > (10 * 1024 * 1024)) {
			redirectAttributes.addFlashAttribute("message", "Arquivo maior que 10MB n&atilde; permitido! ");
			
		} else {
			storageService.store(file);
			redirectAttributes.addFlashAttribute("message", "Upload concluido. Arquivo: " + file.getOriginalFilename() + file.getSize() + "!");
			arquivo = new Arquivos(ObjectId.get(),
					file.getOriginalFilename(), 
					"Teste X", 
					usuario.getNome(), 
					new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
			
			ArquivosClient clientApiArquivos = new ArquivosClient(usuario.getToken()); 
		    clientApiArquivos.createArquivos(arquivo);
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
	

	

}
