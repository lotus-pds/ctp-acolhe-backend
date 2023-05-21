package br.edu.ifsp.spo.ctpacolhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CtpacolheApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtpacolheApplication.class, args);
	}
}
