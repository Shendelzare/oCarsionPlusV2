package com.grupolemon.ocarsionplus.rest;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupolemon.ocarsionplus.dto.in.MarcaDTOIn;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MarcaControllerTest {

	@Autowired
	private MarcaController marcaController;
	@Autowired
	private MockMvc mockMvc;

	private MarcaDTOIn marca;

	@Before
	public void init() {

	}

	@Test
	public void contextLoads() {
		Assert.assertNotNull(marcaController);

	}

	@BeforeEach
	public void setUp() {

		marca = initializaMarca();
	}

	@Test
	public void creaMarcaDevuelveCreated() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(marca);

		mockMvc.perform(MockMvcRequestBuilders.post("/marcas").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void creaMarcaDevuelveBadRequest() throws Exception {

		marca.setNombre("");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(marca);

		mockMvc.perform(MockMvcRequestBuilders.post("/marcas").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	private MarcaDTOIn initializaMarca() {
		MarcaDTOIn nuevaMarca = new MarcaDTOIn();
		nuevaMarca.setNombre("Nissan");
		return nuevaMarca;
	}
}
