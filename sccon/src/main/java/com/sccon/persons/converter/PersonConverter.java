package com.sccon.persons.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sccon.persons.input.dto.PersonInDTO;
import com.sccon.persons.model.PersonEntity;
import com.sccon.persons.output.dto.PersonOutDTO;

@Component
public class PersonConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public PersonOutDTO convert(PersonEntity person) {
		return modelMapper.map(person, PersonOutDTO.class);
	}
	
	public PersonEntity convert(PersonInDTO personInDTO) {
		return modelMapper.map(personInDTO, PersonEntity.class);
	}
	
	public List<PersonOutDTO> convert(List<PersonEntity> persons) {
		return persons.stream().map(p -> convert(p)).collect(Collectors.toList());
	}
}
