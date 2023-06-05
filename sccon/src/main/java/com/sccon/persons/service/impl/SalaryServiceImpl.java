package com.sccon.persons.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sccon.persons.input.enums.OutputSalarioEnum;
import com.sccon.persons.model.PersonEntity;
import com.sccon.persons.model.SalariosConfigEntity;
import com.sccon.persons.repository.SalariosConfigRepository;
import com.sccon.persons.service.PersonService;
import com.sccon.persons.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {
	
	@Autowired
	public PersonService personService;
	
	@Autowired
	public SalariosConfigRepository salariosConfigRepository;
	
	@Override
	public BigDecimal calcularSalario(Integer id, OutputSalarioEnum outputSalario) {
		PersonEntity personEntity = personService.getPersonById(id);
		SalariosConfigEntity salariosConfigEntity = salariosConfigRepository.findById(1).get();
		
		if (OutputSalarioEnum.FULL.equals(outputSalario)) {
			return calcularSalarioEmReais(personEntity, salariosConfigEntity);
		} else {
			return calcularSalarioEmMinimos(personEntity, salariosConfigEntity);
		}
	}
	
	protected BigDecimal calcularSalarioEmReais(PersonEntity personEntity, SalariosConfigEntity salariosConfigEntity) {
		Long anosDeAdmissao = personEntity.getDtAdmissao().until(getDataAtual(), ChronoUnit.YEARS);
		BigDecimal salarioCalculado = salariosConfigEntity.getSalarioInicial();
		BigDecimal percentualAcrescimoAnual = salariosConfigEntity.getPercentualAcrescimoAnual().movePointLeft(2).add(BigDecimal.ONE);
		
		for (int i = 1; i <= anosDeAdmissao; i++) {
			salarioCalculado = salarioCalculado.multiply(percentualAcrescimoAnual);
			salarioCalculado = salarioCalculado.add(salariosConfigEntity.getAcrescimoFixoAnual());
		}
		
		return salarioCalculado.setScale(2, RoundingMode.UP);
	}
	
	protected BigDecimal calcularSalarioEmMinimos(PersonEntity personEntity, SalariosConfigEntity salariosConfigEntity) {
		return calcularSalarioEmReais(personEntity, salariosConfigEntity).divide(salariosConfigEntity.getSalarioMinimo(), 2, RoundingMode.UP);
	}
	
	public LocalDate getDataAtual() {
		return LocalDate.of(2023, 2, 7);
	}
}