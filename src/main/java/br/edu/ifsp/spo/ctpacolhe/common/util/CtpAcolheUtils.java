package br.edu.ifsp.spo.ctpacolhe.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CtpAcolheUtils {	
	public static String formatDateTime(LocalDateTime dateTime) {
		DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		return dateTime.format(CUSTOM_FORMATTER);
	}
	
	public static LocalDateTime toDateTimeAtStartOfDay(LocalDate date) {
		return date.atStartOfDay();
	}
	
	public static LocalDateTime toDateTimeAtEndOfDay(LocalDate date) {
		return date.atTime(LocalTime.MAX);
	}
}
