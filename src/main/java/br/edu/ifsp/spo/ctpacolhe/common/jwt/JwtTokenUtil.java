package br.edu.ifsp.spo.ctpacolhe.common.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil {
	@Autowired
	private JwtConfig jwtConfig;

	public Key getPublicKey() {
		return new SecretKeySpec(Base64.getDecoder().decode(jwtConfig.getSecret()), SignatureAlgorithm.HS256.getJcaName());
	}

	public String generateAccessToken(Usuario usuario) {
		Map<String, Object> claims = Map.of("email", usuario.getEmail(), "roles", usuario.getPerfis().toString());
		
		return Jwts.builder().setSubject(usuario.getIdUsuario().toString())
				.setIssuer(jwtConfig.getIssuer()).addClaims(claims).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getAccessTokenExpiresIn())).signWith(getPublicKey())
				.compact();
	}
	
	public String generateRefreshToken(Usuario usuario, UUID idRenovacaoToken) {
		return Jwts.builder().setSubject(usuario.getIdUsuario().toString())
				.setIssuer(jwtConfig.getIssuer()).claim("email", usuario.getEmail()).setIssuedAt(new Date()).setId(idRenovacaoToken.toString())
				.setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getRefreshTokenExpiresIn())).signWith(getPublicKey())
				.compact();
	}

	public boolean validateJwtToken(String token) {
		try {
			JwtParser immutableJwtParser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
			immutableJwtParser.parseClaimsJws(token);

			return true;
		} catch (ExpiredJwtException e) {
			log.error("JWT expirou", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("Token está nulo, vazio ou é apenas espaços em branco", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("JWT inválido", e);
		} catch (UnsupportedJwtException e) {
			log.error("JWT não suportado", e);
		} catch (SignatureException e) {
			log.error("Falha na assinatura do JWT");
		}

		return false;
	}
	
	public Claims getAllClaimsFromAccessToken(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
		Jws<Claims> c = parser.parseClaimsJws(token);
		return c.getBody();
	}

	public Claims getAllClaimsFromRefreshToken(String token) {
		try {
			JwtParser parser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
			Jws<Claims> c = parser.parseClaimsJws(token);
			return c.getBody();
		} catch (ExpiredJwtException e) {
			log.error("Token de renovação expirou", e.getMessage());
			throw new ValidationException(MensagemExceptionType.TOKEN_RENOVACAO_EXPIROU);
		}
	}

	public Claims decodeToken(String renovacaoToken) {
		validateJwtToken(renovacaoToken);
		return getAllClaimsFromRefreshToken(renovacaoToken);
	}
}
