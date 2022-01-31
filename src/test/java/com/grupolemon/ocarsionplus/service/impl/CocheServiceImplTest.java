package com.grupolemon.ocarsionplus.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Color;
import com.grupolemon.ocarsionplus.model.Extra;
import com.grupolemon.ocarsionplus.model.Marca;
import com.grupolemon.ocarsionplus.model.Precio;
import com.grupolemon.ocarsionplus.repository.CocheRepository;
import com.grupolemon.ocarsionplus.repository.PrecioRepository;
import com.grupolemon.ocarsionplus.service.exceptions.EntityNotFoundException;

@Sql("classpath:data-test.sql")
@SpringBootTest
class CocheServiceImplTest {
	@Mock
	private CocheRepository cocheRepository;

	@Mock
	private PrecioRepository precioRepository;
	@InjectMocks
	private CocheServiceImpl cocheServiceImpl;

	Marca marca;
	Precio precio;
	Coche coche;
	Extra extra;

	@BeforeEach
	public void setUp() {
		marca = inicializaMarca();
		precio = initializePrice();
		coche = initializeCar();
		extra = initializeExtra();

		List<Precio> listaPrecios = new ArrayList<>();
		listaPrecios.add(precio);
		List<Coche> listaCoches = new ArrayList<>();
		precio.setCoche(coche);
		coche.setPrecios(listaPrecios);
		coche.setMarca(marca);
		listaCoches.add(coche);
		marca.setCoche(listaCoches);

	}

	@Test
	void testGetCar() {

		Optional<Coche> optiCoche = Optional.of(coche);
		Optional<Precio> optiPrice = Optional.of(precio);

		Mockito.when(cocheRepository.findById(1L)).thenReturn(optiCoche);
		Mockito.when(precioRepository.findByCocheAndFinFechaVigorAfterAndIniFechaVigorBefore(Mockito.any(Coche.class),
				Mockito.any(LocalDate.class))).thenReturn(optiPrice);

		EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			cocheServiceImpl.getCar(3L, LocalDate.of(2021, 1, 13));
		});

		Assertions.assertEquals("Coche no encontrado", thrown.getMessage());

	}

	@Test
	void testGetCar1() {

		Optional<Coche> optiCoche = Optional.of(coche);
		Mockito.when(cocheRepository.findById(1L)).thenReturn(optiCoche);

		EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			cocheServiceImpl.getCar(1L, null);
		});

		Assertions.assertEquals("Coche no encontrado", thrown.getMessage());

	}

	@Test
	void testGetCar2() {

		Optional<Coche> optiCoche = Optional.of(coche);
		Optional<Precio> optiPrice = Optional.of(precio);

		Mockito.when(cocheRepository.findById(Mockito.anyLong())).thenReturn(optiCoche);
		Mockito.when(precioRepository.findByCocheAndFinFechaVigorAfterAndIniFechaVigorBefore(Mockito.any(Coche.class),
				Mockito.any(LocalDate.class))).thenReturn(optiPrice);
		Assertions.assertNotNull(cocheServiceImpl.getCar(3L, LocalDate.of(2022, 1, 13)));

	}

	@Test
	void testGet() {

		List<Coche> carList = new ArrayList<>();
		Mockito.when(cocheRepository.findAll()).thenReturn(carList);
		Assertions.assertNotNull(cocheServiceImpl.get());

	}

	@Test
	void testGet1() {
		List<Coche> carList = new ArrayList<>();
		carList.add(coche);
		Mockito.when(cocheRepository.findAll()).thenReturn(carList);
		Assertions.assertNotNull(cocheServiceImpl.get("color eq azul"));

	}

	@Test
	void testGet2() {

		List<Coche> carList = new ArrayList<>();
		carList.add(coche);
		Mockito.when(cocheRepository.findAll()).thenReturn(carList);
		Assertions.assertNotNull(cocheServiceImpl.get("marca eq rojo"));

	}

	@Test
	void testGet3() {

		List<Coche> carList = new ArrayList<>();
		carList.add(coche);
		Optional<Precio> optiPrice = Optional.of(precio);

		Mockito.when(cocheRepository.findAll(Mockito.any(Specification.class))).thenReturn(carList);
		Mockito.when(precioRepository.findByCocheAndFinFechaVigorAfterAndIniFechaVigorBefore(Mockito.any(Coche.class),
				Mockito.any(LocalDate.class))).thenReturn(optiPrice);
		Assertions.assertEquals(1, cocheServiceImpl.get("color eq NEGRO").getCoches().size());

	}

	private Coche initializeCar() {
		Coche coche = new Coche();
		coche.setColor(Color.NEGRO);
		coche.setId(1L);
		coche.setNombreModelo("Mustang");
		return coche;

	}

	private Marca inicializaMarca() {
		Marca brand = new Marca();
		brand.setNombre("Ford");
		brand.setId(1L);
		List<Coche> cars = new ArrayList<>();
		brand.setCoche(cars);

		return brand;

	}

	private Precio initializePrice() {
		Precio price = new Precio();
		price.setIniFechaVigor(LocalDate.of(1998, 06, 01));
		price.setId(1L);
		price.setFinFechaVigor(LocalDate.of(2023, 06, 01));
		price.setValor(21.000);
		return price;

	}

	private Extra initializeExtra() {
		Extra extra = new Extra();
		extra.setId(1L);
		extra.setNombre("Porton trasero electrico");
		return extra;

	}

	private static String toString(Throwable e) {
		StringBuffer sb = new StringBuffer();
		sb.append(e.toString()).append("\n");
		for (StackTraceElement st : e.getStackTrace()) {
			sb.append(" at ").append(st.toString()).append("\n");
		}
		return sb.toString();
	}
}
