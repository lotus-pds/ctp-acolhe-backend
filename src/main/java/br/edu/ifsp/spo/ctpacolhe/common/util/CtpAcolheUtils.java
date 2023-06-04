package br.edu.ifsp.spo.ctpacolhe.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CtpAcolheUtils {	
	public static String formatDateTime(LocalDateTime dateTime) {
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		return dateTime.format(CUSTOM_FORMATTER);
	}
	
	public static LocalDateTime getDateTimeBrazil() {
		ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
		
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
		
		return zonedDateTime.toLocalDateTime();
	}
}
