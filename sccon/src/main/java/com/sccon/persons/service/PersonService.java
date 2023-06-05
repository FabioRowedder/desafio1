package com.sccon.persons.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.sccon.persons.input.dto.PersonInDTO;
import com.sccon.persons.input.enums.OutputPeriodoEnum;
import com.sccon.persons.model.PersonEntity;

public interface PersonService {

	public List<PersonEntity> getPersons();
	
	public PersonEntity getPersonById(Integer id);
	
	public PersonEntity addPerson(PersonInDTO personInDTO);
	
	public void deleteById(Integer id);
	
	public void update(Integer id, PersonInDTO personInDTO);
	
	public void patch(Integer id, Map<String, Object> updates);
	
	public Long getAge(Integer id, OutputPeriodoEnum outputPeriodo);
	
	public LocalDate getDataAtual();
	
}
