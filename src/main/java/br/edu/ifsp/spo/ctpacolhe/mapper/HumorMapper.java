package br.edu.ifsp.spo.ctpacolhe.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import br.edu.ifsp.spo.ctpacolhe.dto.HumorDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;

@Mapper(componentModel = "spring")
public interface HumorMapper {
	HumorDto to(Humor humor);
	Page<HumorDto> to(Page<Humor> humores);
}
