package br.edu.ifsp.spo.ctpacolhe.common.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("jwt")
@Getter
@Setter
public class JwtConfig {
	private String issuer;
	private String secret;
    private Integer accessTokenExpiresIn;
    private Integer refreshTokenExpiresIn;
}
