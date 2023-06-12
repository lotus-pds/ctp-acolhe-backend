package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.HumorDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;

@Mapper(componentModel = "spring")
public interface HumorMapper {
	HumorDto to(Humor humor);
	List<HumorDto> to(List<Humor> humores);
}
