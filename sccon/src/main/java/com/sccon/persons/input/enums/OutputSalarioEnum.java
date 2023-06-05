package com.sccon.persons.input.enums;

import org.yaml.snakeyaml.util.EnumUtils;

public enum OutputSalarioEnum {

	FULL, MIN;
	
	public static OutputSalarioEnum from(String value) {
		try {
			return EnumUtils.findEnumInsensitiveCase(OutputSalarioEnum.class, value);
		} catch (IllegalArgumentException iae) {
			return null;
		}
	}
}
