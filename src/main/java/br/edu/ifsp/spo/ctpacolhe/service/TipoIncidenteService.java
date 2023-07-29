package br.edu.ifsp.spo.ctpacolhe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente;
import br.edu.ifsp.spo.ctpacolhe.repository.TipoIncidenteRepository;

@Service
@Transactional
public class TipoIncidenteService {
	
	@Autowired
	private TipoIncidenteRepository tipoIncidenteRepository;
	
	public Page<TipoIncidente> buscaTipos(FiltroWrapper filtroWrapper) {
		Page<TipoIncidente> tipos = tipoIncidenteRepository.findAll(filtroWrapper);
		return tipos;
	}

}
