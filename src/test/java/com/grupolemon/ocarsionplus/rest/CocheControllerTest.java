package com.grupolemon.ocarsionplus.rest;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupolemon.ocarsionplus.dto.in.CocheDTOIn;
import com.grupolemon.ocarsionplus.model.Color;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("classpath:CocheControllerTest.sql")
class CocheControllerTest {

	@Autowired
	private CocheController cocheController;
	@Autowired
	private MockMvc mockMvc;

	private CocheDTOIn coche;
	@Before
	public void init() {

	}

	@Test
	public void contextLoads() {
		Assert.assertNotNull(cocheController);

	}
	@BeforeEach
	public void setUp() {

		coche = initializaCoche();
	}

	@Test
	public void creaCocheDevuelveCreated() throws Exception {
	
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(coche);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/coches").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void creaCocheDevuelveBadRequest() throws Exception {
	
		coche.setIdMarca(2L);
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(coche);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/coches").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
	private CocheDTOIn initializaCoche() {
		CocheDTOIn coche = new CocheDTOIn();
		coche.setCilindrada(3005);
		coche.setPotencia(105);
		coche.setNombreModelo("Xsara");
		coche.setColor(Color.AZUL);
		coche.setIdMarca(1L);
		return coche;

	}
}
