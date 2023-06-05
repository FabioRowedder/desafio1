package com.sccon.persons.output.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonOutDTO {

	private Integer id;
	
	private String nome;
	
	private LocalDate dtNascimento;
	
	private LocalDate dtAdmissao;
}
