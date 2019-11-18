package com.itau.demo.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import com.itau.demo.models.Acessos;

public class AcessosClient {
	
	private RestOperations restOperations;
	private HttpHeaders headers;
	
	final private String url;		
		
		public AcessosClient(String accessToken, @Value("${gestao_arquivos.servicos.url}") final String url) {
			this.url = url + "acessos/";
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer "+ accessToken);
		}
		
		public Acessos getAcessos(final int numero) {

			return restOperations.getForObject(url, Acessos.class, numero);
		}
		
		public Acessos createAcesso(Acessos acesso) {
			HttpEntity<Acessos> entity = new HttpEntity<>(acesso, headers);
			return restOperations.postForObject(url, entity, Acessos.class);
		}

}
