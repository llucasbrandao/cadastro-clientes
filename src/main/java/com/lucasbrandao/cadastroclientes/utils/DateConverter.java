package com.lucasbrandao.cadastroclientes.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateConverter {
	
	public static LocalDate dateToLocalDateConverter(Date dateToConvert) {
		return dateToConvert == null ? null : dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Integer getYearsDifferenceFromNow(LocalDate date) {
		return date == null ? 0 : (int) ChronoUnit.YEARS.between(date, LocalDate.now());
	}
}
