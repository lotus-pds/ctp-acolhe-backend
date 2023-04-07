package br.edu.ifsp.spo.ctpacolhe.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

@Component
public class JwtTokenUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	public static final long EXPIRE_DURATION = 120 * 60000; // 2 horas

	private String SECRET_KEY = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

	public Key getPublicKey() {
		return new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), SignatureAlgorithm.HS256.getJcaName());
	}

	public String generateAccessToken(Usuario usuario) {

		return Jwts.builder().setSubject(String.format("%s,%s", usuario.getIdUsuario(), usuario.getEmail()))
				.setIssuer("CTP Acolhe").claim("roles", usuario.getPerfil().toString()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)).signWith(getPublicKey())
				.compact();
	}

	public boolean validateAccessToken(String token) {
		try {
			JwtParser immutableJwtParser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
			immutableJwtParser.parseClaimsJws(token);

			return true;
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expirou", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token está nulo, vazio ou é apenas espaços em branco", ex.getMessage());
		} catch (MalformedJwtException ex) {
			LOGGER.error("JWT inválido", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT não suportado", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Falha na assinatura do JWT");
		}

		return false;
	}

	public String getSubject(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Claims getAllClaimsFromToken(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
		Jws<Claims> c = parser.parseClaimsJws(token);
		return c.getBody();
	}
}
