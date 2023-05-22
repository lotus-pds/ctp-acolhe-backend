package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.exception.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.jwt.JwtTokenUtil;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Perfil;
import br.edu.ifsp.spo.ctpacolhe.entity.RenovacaoToken;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.RenovacaoTokenRepository;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;
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
	
	@Value("${jwt.access-token-expires-in}")
	private long EXPIRE_DURATION;
	
	public AcessoDto montaAutenticacao(AcessoCreateDto acessoDto) {
		Usuario usuario = getUsuario(acessoDto.getEmail());
		
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
			
			List<String> perfis = usuario.getPerfis().stream().map(Perfil::getDescricao).toList();
			
			AcessoDto dto = new AcessoDto(acessoToken, renovacaoTokenString, perfis, System.currentTimeMillis() + EXPIRE_DURATION);
			
			log.info("Acesso feito com sucesso para o e-mail {}", usuarioAutenticado.getEmail());
			return dto;
		} catch(BadCredentialsException ex) {
			throw new ValidationException(MensagemExceptionType.CREDENCIAIS_INCORRETAS);
		}
	}
	
	private Usuario getUsuario(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ValidationException(MensagemExceptionType.CREDENCIAIS_INCORRETAS));
    }
}
