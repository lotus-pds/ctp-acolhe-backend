package br.edu.ifsp.spo.ctpacolhe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;
import br.edu.ifsp.spo.ctpacolhe.repository.IncidenteRepository;

@Service
@Transactional
public class IncidenteService {
	
	@Autowired
	private IncidenteRepository incidenteRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Page<Incidente> buscaIncidentes(FiltroWrapper filtroWrapper) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();
		
		IncidenteFiltro filtro = (IncidenteFiltro) filtroWrapper.getFiltro();
		filtro.setIdUsuarioOrigem(usuarioAutenticado.getIdUsuario());
		
		return incidenteRepository.findAllBy(filtroWrapper);
	}
}
