package br.edu.ifsp.spo.ctpacolhe.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
	
	@Bean
	public OpenAPI myOpenAPI() {
		Info info = new Info().title("CTP Acolhe - Documentação da API").version("1.0")
				.description("Exemplos para os recursos da API CTP Acolhe.");

		return new OpenAPI().info(info);
	}
}
