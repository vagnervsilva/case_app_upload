package com.itau.demo.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.itau.demo.models.Arquivos;

public class ArquivosClient {
	
	private RestOperations restOperations;
	//private String bearerToken;

	
		static final String url = "http://localhost:8080/arquivos/";
		
		
		public ArquivosClient(String accessToken ) {

			//bearerToken = accessToken;


		}
		
		public Arquivos getArquivos(final int numero) {

			return restOperations.getForObject(url, Arquivos.class, numero);
		}
		
		public Arquivos createArquivos(Arquivos arquivo) {
		      HttpHeaders headers = new HttpHeaders();
		      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		      headers.setContentType(MediaType.APPLICATION_JSON);
		      //headers.add("Authorization", "Bearer eyJraWQiOiJDdlB6dzZyLUZtX0RzNEM3Y3dtd1Q4YUc2T2FHSjc3NjBNU204a1U2TldZIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULlRiUFpvcnpRanJCY0FoY3NDT0NtdVJUT0ZtWmtmTlZfRWYtR3NGZXc0dnciLCJpc3MiOiJodHRwczovL2Rldi04NjY4Mzgub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNTc0MDMzNTYxLCJleHAiOjE1NzQwMzcxNjEsImNpZCI6IjBvYTF1OWxwNjZjYkdlNjRSMzU3IiwidWlkIjoiMDB1MXU4amYyaXNIaDhTN0MzNTciLCJzY3AiOlsib3BlbmlkIl0sInN1YiI6InZhZ25lcnZzaWx2YUBnbWFpbC5jb20ifQ.Ey2EBMzO6B6X-Ri250tatUV5F-kdLpC9i-Hjw55sS500WEcy7T6l0WZ8Xuo-Ow3Enjkm0tL9j_C4iRpe7lU_LSpV-Nz0-ybnK3131HD1nieP599n8YGqcKDtd5nAne9QHpyo2CarIJAqYmPr0PU14_c3Mo6fZu9KVYZYKiwDRs2566dMQ7q6hIuo9ovlKBkewaOLX5zLX4RR85phlseDHi1FIM6v-ngGj48ocCSBzheVuf-0sdcJSrWKc5gaHn1dYuRb6vUt_-L6EoiyKdrIi_1MsMdG4c6ePmWwuzQZQzGydzP2Akd8dk_LcwDtCglyglhI7BCpGtTw_T1lLSzudA");
		      //headers.add("Authorization", "Bearer " + bearerToken);
		 
		      RestTemplate restTemplate = new RestTemplate();
		 

		      HttpEntity<Arquivos> requestBody = new HttpEntity<>(arquivo, headers);
		 

		      Arquivos arquivoCriado = restTemplate.postForObject(url, requestBody, Arquivos.class);
		 
		      return arquivoCriado;
		}

}
