package com.sccon.persons.service;

import java.math.BigDecimal;

import com.sccon.persons.input.enums.OutputSalarioEnum;

public interface SalaryService {

	BigDecimal calcularSalario(Integer id, OutputSalarioEnum outputSalario);

}