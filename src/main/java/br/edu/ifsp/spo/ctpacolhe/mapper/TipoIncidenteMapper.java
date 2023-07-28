package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.TipoIncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente;

@Mapper(componentModel = "spring")
public interface TipoIncidenteMapper {
	TipoIncidenteDto to(TipoIncidente tipo);
	List<TipoIncidenteDto> to(List<TipoIncidente> tipos);
}
