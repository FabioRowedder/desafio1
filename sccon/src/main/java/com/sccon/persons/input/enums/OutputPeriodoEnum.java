package com.sccon.persons.input.enums;

import java.time.temporal.ChronoUnit;

import org.yaml.snakeyaml.util.EnumUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OutputPeriodoEnum {

	DAYS(ChronoUnit.DAYS), 
	MONTHS(ChronoUnit.MONTHS), 
	YEARS(ChronoUnit.YEARS);
	
	private ChronoUnit chronoUnit;
	
	public static OutputPeriodoEnum from(String value) {
		try {
			return EnumUtils.findEnumInsensitiveCase(OutputPeriodoEnum.class, value);
		} catch (IllegalArgumentException iae) {
			return null;
		}
	}
}
