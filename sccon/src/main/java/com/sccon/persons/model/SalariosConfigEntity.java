package com.sccon.persons.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SALARIOS_CONFIG")
public class SalariosConfigEntity {

	@Id
	private Integer id;
	
	private BigDecimal salarioInicial;
	
	private BigDecimal acrescimoFixoAnual;
	
	private BigDecimal percentualAcrescimoAnual;
	
	private BigDecimal salarioMinimo;
	
}
