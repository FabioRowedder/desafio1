package com.sccon.persons.service.impl;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.sccon.persons.converter.PersonConverter;
import com.sccon.persons.exception.PersonIdAlreadyExistsException;
import com.sccon.persons.exception.PersonNotFoundException;
import com.sccon.persons.input.dto.PersonInDTO;
import com.sccon.persons.input.enums.OutputPeriodoEnum;
import com.sccon.persons.model.PersonEntity;
import com.sccon.persons.repository.ListPersonRepository;
import com.sccon.persons.repository.PersonRepository;
import com.sccon.persons.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	public PersonRepository personRepository;
	
	@Autowired
	public ListPersonRepository listPersonRepository;
	
	@Autowired
	public PersonConverter personConverter;
	
	@Autowired
	public MessageSource messageSource;
	
	@Override
	public List<PersonEntity> getPersons() {
		return listPersonRepository.findAll(Sort.by("nome"));
	}
	
	@Override
	public PersonEntity getPersonById(Integer id) {
		Optional<PersonEntity> optionalPerson = personRepository.findById(id);
		
		if (optionalPerson.isEmpty()) {
			throw new PersonNotFoundException(getMessage("pessoa.not.found"));
		}
			
		return optionalPerson.get();
	}
	
	@Override
	public PersonEntity addPerson(PersonInDTO personInDTO) {
		PersonEntity person = personConverter.convert(personInDTO);
		
		if (null != person.getId()) {
			if (Boolean.TRUE.equals(personRepository.existsById(person.getId()))) {
				throw new PersonIdAlreadyExistsException(getMessage("id.conflict"));
			}
		} else {
			Integer lastIdFound = personRepository.getMaxId();
			person.setId(++lastIdFound);
		}
		
		return personRepository.save(person);
	}
	
	@Override
	public void deleteById(Integer id) {
		if (Boolean.TRUE.equals(personRepository.existsById(id))) {
			personRepository.deleteById(id);
		} else {
			throw new PersonNotFoundException(getMessage("pessoa.not.found"));
		}
	}
	
	@Override
	public void update(Integer id, PersonInDTO personInDTO) {
		PersonEntity actualPersonEntity = getPersonById(id);
		BeanUtils.copyProperties(personInDTO, actualPersonEntity, "id");
		personRepository.save(actualPersonEntity);
	}
	
	@Override
	public void patch(Integer id, Map<String, Object> updates) {
		PersonEntity personEntity = getPersonById(id);
		merge(personEntity, updates);
		personRepository.save(personEntity);
	}
	
	@Override
	public Long getAge(Integer id, OutputPeriodoEnum outputPeriodo) {
		PersonEntity personEntity = getPersonById(id);
		
		return personEntity.getDtNascimento().until(getDataAtual(), outputPeriodo.getChronoUnit());
	}
	
	protected void merge(PersonEntity personEntity, Map<String, Object> updates) {
		updates.forEach((nomeCampo, valorCampo) -> {
			Field campo = ReflectionUtils.findField(PersonEntity.class, nomeCampo);
			if (null != campo) {
				campo.setAccessible(true);
				
				if (campo.getType().equals(LocalDate.class)) {
					ReflectionUtils.setField(campo, personEntity, LocalDate.parse(valorCampo.toString(), DateTimeFormatter.ISO_DATE));
				} else {
					ReflectionUtils.setField(campo, personEntity, valorCampo);
				}
			}
		});
	}
	
	private String getMessage(String keyMessage) {
		return messageSource.getMessage(keyMessage, null, Locale.getDefault());
	}
	
	public LocalDate getDataAtual() {
		return LocalDate.of(2023, 2, 7);
	}
}
