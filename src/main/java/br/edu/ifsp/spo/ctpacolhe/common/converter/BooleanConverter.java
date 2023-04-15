package br.edu.ifsp.spo.ctpacolhe.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return attribute ? "S" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return dbData != null && dbData.equalsIgnoreCase("S");
	}
}
