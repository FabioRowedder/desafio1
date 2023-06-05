package com.sccon.persons.input.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonInDTO {

	@Min(value = 1, message = "{beanvalidation.valor.minimo}")
	@Max(value = 1000000, message = "{beanvalidation.valor.maximo}")
	private Integer id;
	
	@NotEmpty(message = "{beanvalidation.campo.obrigatorio}")
	private String nome;
	
	@NotNull(message = "{beanvalidation.campo.obrigatorio}")
	private LocalDate dtNascimento;
	
	@NotNull(message = "{beanvalidation.campo.obrigatorio}")
	private LocalDate dtAdmissao;
}
