package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface Controller {
	default URI uriCreated(String path, Object... id) {
		String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		UriComponents uriNovaEntidade = UriComponentsBuilder.newInstance().path(uriString + "/" + path)
				.buildAndExpand(id);
		return uriNovaEntidade.toUri();
	}

	default <DTO extends Object> ResponseEntity<Page<DTO>> respostaPaginada(Page<DTO> resultado) {
		HttpHeaders responseHeaders = new HttpHeaders();
		if (resultado.hasPrevious() || resultado.hasNext()) {
			responseHeaders.add(HttpHeaders.CONTENT_RANGE, "items " + resultado.getNumber() + "-"
					+ (resultado.getNumber() + resultado.getNumberOfElements()) + "/" + resultado.getTotalElements());
			return new ResponseEntity<>(resultado, responseHeaders, HttpStatus.PARTIAL_CONTENT);
		} else {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
}
