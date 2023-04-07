package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.dto.AcessoDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.jwt.JwtTokenUtil;

@Service
@Transactional
public class AcessoService {
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	public AcessoDto montaAutenticacao(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		String acessoToken = jwtUtil.generateAccessToken(usuario);
		
		List<String> perfis = new ArrayList<>();
		perfis.add(usuario.getPerfil().getCodigo());
		
		AcessoDto dto = new AcessoDto(acessoToken, perfis,
				System.currentTimeMillis() + JwtTokenUtil.EXPIRE_DURATION);

		return dto;
	}
}
