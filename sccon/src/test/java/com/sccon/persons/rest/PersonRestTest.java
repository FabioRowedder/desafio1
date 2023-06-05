package com.sccon.persons.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sccon.persons.ScconApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
				classes = ScconApplication.class)
public class PersonRestTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
	}
	
	@Test
	public void buscarSalarioEmReaisTest() throws Exception {
		Integer personId = 1;
		String periodo = "full";
		Double salarioEsperado = 3259.36;
		
		mvc.perform(
				get("/persons/{id}/salary", personId)
				.param("output", periodo)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("salario", is(salarioEsperado)));
	}
	
	@Test
	public void buscarSalarioConvertendoParaSalariosMinimosTest() throws Exception {
		Integer personId = 1;
		String periodo = "min";
		Double salarioEsperado = 2.51;
		
		mvc.perform(
				get("/persons/{id}/salary", personId)
				.param("output", periodo)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("salario", is(salarioEsperado)));
	}
	
	@Test
	public void calcularIdadeEmDiasTest() throws Exception {
		Integer personId = 1;
		String periodo = "days";
		Integer idadeEsperada = 8342;
		
		mvc.perform(
				get("/persons/{id}/age", personId)
				.param("output", periodo)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("idade", is(idadeEsperada)));
	}
	
	@Test
	public void calcularIdadeEmMesesTest() throws Exception {
		Integer personId = 1;
		String periodo = "months";
		Integer idadeEsperada = 274;
		
		mvc.perform(
				get("/persons/{id}/age", personId)
				.param("output", periodo)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("idade", is(idadeEsperada)));
	}
	
	@Test
	public void calcularIdadeEmAnosTest() throws Exception {
		Integer personId = 1;
		String periodo = "years";
		Integer idadeEsperada = 22;
		
		mvc.perform(
				get("/persons/{id}/age", personId)
				.param("output", periodo)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("idade", is(idadeEsperada)));
	}
}
