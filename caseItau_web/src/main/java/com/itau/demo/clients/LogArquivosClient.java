package com.itau.demo.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.itau.demo.models.LogArquivos;

public class LogArquivosClient {
	
	private RestOperations restOperations;

	
		static final String url = "http://localhost:8080/logArquivos/";
		
		
		public LogArquivosClient( ) {


		}
		
		public LogArquivos getLogArquivos(final int numero) {

			return restOperations.getForObject(url, LogArquivos.class, numero);
		}
		
		public List<LogArquivos> getLogArquivosPorNome(final String nomeArquivo) {
		    RestTemplate restTemplate = new RestTemplate();
			String urlFinal = url + "nome/";
			ResponseEntity<LogArquivos[]> response = restTemplate.getForEntity(urlFinal, LogArquivos[].class, nomeArquivo);
			return Arrays.asList(response.getBody());
		}
		
		public LogArquivos createLogArquivos(LogArquivos log) {
		      HttpHeaders headers = new HttpHeaders();
		      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		      headers.setContentType(MediaType.APPLICATION_JSON);
		 
		      RestTemplate restTemplate = new RestTemplate();		 

		      HttpEntity<LogArquivos> requestBody = new HttpEntity<>(log, headers);		 

		      LogArquivos arquivoCriado = restTemplate.postForObject(url, requestBody, LogArquivos.class);
		 
		      return arquivoCriado;
		}

}
