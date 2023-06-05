package com.sccon.persons.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sccon.persons.converter.PersonConverter;
import com.sccon.persons.exception.ValidationException;
import com.sccon.persons.input.dto.PersonInDTO;
import com.sccon.persons.input.enums.OutputPeriodoEnum;
import com.sccon.persons.input.enums.OutputSalarioEnum;
import com.sccon.persons.model.PersonEntity;
import com.sccon.persons.output.dto.PersonOutDTO;
import com.sccon.persons.service.PersonService;
import com.sccon.persons.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonRest {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private PersonConverter personConverter;
	
	@Autowired
	public MessageSource messageSource;
	
	@GetMapping
	public ResponseEntity<List<PersonOutDTO>> getAll() {
		List<PersonEntity> persons = personService.getPersons();
		
		return ResponseEntity.ok(personConverter.convert(persons));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonOutDTO> getById(@PathVariable("id") Integer id) {
		PersonEntity person = personService.getPersonById(id);
		
		return ResponseEntity.ok(personConverter.convert(person));
	}
	
	@PostMapping
	public ResponseEntity<PersonOutDTO> add(@RequestBody @Valid PersonInDTO personInDTO, UriComponentsBuilder uriBuilder) {
		PersonEntity person = personService.addPerson(personInDTO);
		
		URI uri = uriBuilder.path("/persons/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(personConverter.convert(person));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
		personService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid PersonInDTO personInDTO) {
		personService.update(id, personInDTO);

		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> patch(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
		personService.patch(id, updates);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/age")
	public ResponseEntity<Map<String, Long>> calcularIdade(@PathVariable("id") Integer id, @RequestParam("output") String outputPeriodo) {
		// Validação do parâmetro outputPeriodo não feita com @Pattern por apresentar issue de segurança (DOS)
		OutputPeriodoEnum outputPeriodoEnum = OutputPeriodoEnum.from(outputPeriodo);
		if (null == outputPeriodoEnum) {
			throw new ValidationException(getMessage("outputPeriodo.validation.error.message"));
		}
		
		Long idade = personService.getAge(id, outputPeriodoEnum);
		
		return ResponseEntity.ok(Collections.singletonMap("idade", idade));
	}
	
	@GetMapping("/{id}/salary")
	public ResponseEntity<Map<String, BigDecimal>> getSalario(@PathVariable("id") Integer id, @RequestParam("output") String outputSalario) {
		// Validação do parâmetro outputSalario não feita com @Pattern por apresentar issue de segurança (DOS)
		OutputSalarioEnum outputSalarioEnum = OutputSalarioEnum.from(outputSalario);
		if (null == outputSalarioEnum) {
			throw new ValidationException(getMessage("outputSalario.validation.error.message"));
		}
		
		BigDecimal salario = salaryService.calcularSalario(id, OutputSalarioEnum.from(outputSalario));
		
		return ResponseEntity.ok(Collections.singletonMap("salario", salario));
	}
	
	private String getMessage(String keyMessage) {
		return messageSource.getMessage(keyMessage, null, Locale.getDefault());
	}
}
