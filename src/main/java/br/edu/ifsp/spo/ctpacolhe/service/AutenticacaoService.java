package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.exception.CamposDinamicosType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.jwt.JwtConfig;
import br.edu.ifsp.spo.ctpacolhe.common.jwt.JwtTokenUtil;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoDto;
import br.edu.ifsp.spo.ctpacolhe.entity.RenovacaoToken;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.RenovacaoTokenRepository;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AutenticacaoService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private RenovacaoTokenRepository renovacaoTokenRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	public AcessoDto montaAutenticacao(AcessoCreateDto acessoDto) {
		Usuario usuario = buscaUsuario(acessoDto.getEmail());
		
		if(!usuario.getEmailConfirmado()) {
			throw new ValidationException(MensagemExceptionType.EMAIL_NAO_CONFIRMADO);
		}
		
		try {			
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(acessoDto.getEmail(), acessoDto.getSenha()));
			
			Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
			
			renovacaoTokenRepository.deleteAllByIdUsuario(usuarioAutenticado.getIdUsuario());
			
			String acessoToken = jwtUtil.generateAccessToken(usuarioAutenticado);
			
			UUID idRenovacaoToken = UUID.randomUUID();
			String renovacaoTokenString = jwtUtil.generateRefreshToken(usuarioAutenticado, idRenovacaoToken);
			RenovacaoToken renovacaoToken = new RenovacaoToken(idRenovacaoToken, renovacaoTokenString, usuarioAutenticado.getIdUsuario());
			renovacaoTokenRepository.save(renovacaoToken);
			
			List<String> perfis = usuarioAutenticado.getPerfisString();
			
			AcessoDto dto = new AcessoDto(acessoToken, renovacaoTokenString, perfis, System.currentTimeMillis() + jwtConfig.getAccessTokenExpiresIn());			
			log.info("Acesso feito com sucesso para o e-mail {}", usuarioAutenticado.getEmail());
			return dto;
		} catch(BadCredentialsException ex) {
			throw new ValidationException(MensagemExceptionType.CREDENCIAIS_INCORRETAS);
		}
	}
	
	public AcessoDto renovacaoToken(String renovacaoTokenString) {
		Claims decodedToken = jwtUtil.decodeToken(renovacaoTokenString);

        UUID idToken = UUID.fromString(decodedToken.getId());
        UUID idUsuario = UUID.fromString(decodedToken.getSubject());
        
        Usuario usuario = buscaUsuario(idUsuario);
        
		RenovacaoToken renovacaoToken = renovacaoTokenRepository.findById(idToken)
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.TOKEN_NAO_ENCONTRADO,
						CamposDinamicosType.RENOVACAO_TOKEN));
        
        String novoAcessoToken = jwtUtil.generateAccessToken(usuario);
        String novoRenovacaoToken = jwtUtil.generateRefreshToken(usuario, UUID.randomUUID());
        
        renovacaoToken.setToken(novoRenovacaoToken);
        renovacaoTokenRepository.save(renovacaoToken);
        
        List<String> perfis = usuario.getPerfisString();
        
        AcessoDto dto = new AcessoDto(novoAcessoToken, novoRenovacaoToken, perfis, System.currentTimeMillis() + jwtConfig.getAccessTokenExpiresIn());
        
        log.info("Renovação do token feito com sucesso para o e-mail {}", usuario.getEmail());
		return dto;
	}
	
	public void sair(Authentication usuarioAutenticado) {
		Usuario usuario = (Usuario) usuarioAutenticado.getPrincipal();
		
		renovacaoTokenRepository.deleteAllByIdUsuario(usuario.getIdUsuario());
        log.info("Usuário com e-mail {} saiu da sessão com sucesso", usuario.getEmail());
	}
	
	private Usuario buscaUsuario(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ValidationException(MensagemExceptionType.CREDENCIAIS_INCORRETAS));
    }
	
	private Usuario buscaUsuario(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ValidationException(MensagemExceptionType.CREDENCIAIS_INCORRETAS));
    }

}
