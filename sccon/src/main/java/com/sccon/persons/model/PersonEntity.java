package com.sccon.persons.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PESSOAS")
public class PersonEntity {

	@Id
	private Integer id;
	
	private String nome;
	
	private LocalDate dtNascimento;
	
	private LocalDate dtAdmissao;
}
