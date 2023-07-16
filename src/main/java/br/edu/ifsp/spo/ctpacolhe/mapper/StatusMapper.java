package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.StatusDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
	StatusDto to(Status status);
	List<StatusDto> to(List<Status> status);
}
