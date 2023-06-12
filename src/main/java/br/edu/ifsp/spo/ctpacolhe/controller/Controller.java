package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import org.springframework.http.MediaType;
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
}
