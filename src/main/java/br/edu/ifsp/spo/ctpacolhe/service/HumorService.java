package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDate;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.HumorFiltro;
import br.edu.ifsp.spo.ctpacolhe.repository.HumorRepository;

@Service
@Transactional
public class HumorService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HumorRepository humorRepository;

	public Humor criaHumor(HumorCreateDto dto) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();

		if (humorRepository.existsByDataHumorAndIdUsuario(LocalDate.now(), usuarioAutenticado.getIdUsuario())) {
			throw new ValidationException(MensagemExceptionType.HUMOR_REGISTRADO_HOJE);
		}

		Humor humor = Humor.builder()
				.idHumor(UUID.randomUUID())
				.dataHumor(LocalDate.now())
				.idUsuario(usuarioAutenticado.getIdUsuario())
				.idSentimento(dto.getIdSentimento())
				.descricao(dto.getDescricao())
				.build();

		return humorRepository.save(humor);
	}

	public Page<Humor> buscaHumores(FiltroWrapper filtroWrapper) {		
		HumorFiltro filtro = (HumorFiltro) filtroWrapper.getFiltro();
		filtro.setUsuario(usuarioService.buscaUsuarioAutenticado());
		
		Page<Humor> humores = humorRepository.findAll(filtroWrapper);
		
		return humores;
	}
}
