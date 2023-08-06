package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaDto;
import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala;

@Mapper(componentModel = "spring")
public interface AgendamentoSalaMapper {
	AgendamentoSalaDto to(AgendamentoSala agendamento);
	List<AgendamentoSalaDto> to(List<AgendamentoSala> agendamentos);
}
