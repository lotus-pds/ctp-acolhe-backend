package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface Controller {
	
	public static final String QUANTIDADE_TOTAL = "Quantidade-Total";
	
	default URI uriCreated(String path, Object... id) {
		String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		UriComponents uriNovaEntidade = UriComponentsBuilder.newInstance().path(uriString + "/" + path)
				.buildAndExpand(id);
		return uriNovaEntidade.toUri();
	}
	
	default BodyBuilder respostaPaginada(Page<?> page) {
		Long totalRegistros = page.getTotalElements();
		Integer totalRegistrosPage = page.getSize();
		Integer totalPages = page.getTotalPages();
		Integer numeroPagina = page.getPageable().getPageNumber();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(QUANTIDADE_TOTAL, String.valueOf(totalRegistros));
		
		BodyBuilder status = ResponseEntity.ok();
		
		if ((totalRegistrosPage < totalRegistros) && !(totalPages.equals(numeroPagina + 1))) {
			status = ResponseEntity.status(HttpStatus.PARTIAL_CONTENT);
		}
		
		return status.headers(responseHeaders);
	}
}
