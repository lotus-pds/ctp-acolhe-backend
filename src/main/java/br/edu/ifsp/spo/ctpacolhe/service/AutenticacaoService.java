package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;

@Service
@Transactional
public class AutenticacaoService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public AcessoDto montaAutenticacao(AcessoCreateDto acessoDto) {
		Usuario usuario = getUsuario(acessoDto.getEmail());
		
		if(!usuario.getEmailConfirmado()) {
			throw new ValidationException(MensagemExceptionType.AUT_EMAIL_NAO_CONFIRMADO);
		}
		
		try {			
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(acessoDto.getEmail(), acessoDto.getSenha()));
			
			Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
			String acessoToken = jwtUtil.generateAccessToken(usuarioAutenticado);
			
			List<String> perfis = usuario.getPerfis().stream().map(Perfil::getDescricao).toList();
			
			AcessoDto dto = new AcessoDto(acessoToken, perfis,
					System.currentTimeMillis() + JwtTokenUtil.EXPIRE_DURATION);

			return dto;
		} catch(BadCredentialsException ex) {
			throw new ValidationException(MensagemExceptionType.AUT_CREDENCIAIS_INCORRETAS);
		}
	}
	
	private Usuario getUsuario(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ValidationException(MensagemExceptionType.AUT_CREDENCIAIS_INCORRETAS)
        );
    }
}
