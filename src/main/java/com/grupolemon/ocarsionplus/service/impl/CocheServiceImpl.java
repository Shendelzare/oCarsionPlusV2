package com.grupolemon.ocarsionplus.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.grupolemon.ocarsionplus.dto.CocheDTO;
import com.grupolemon.ocarsionplus.dto.CochePorFechaDTO;
import com.grupolemon.ocarsionplus.dto.CocheFiltradoWrapperDTO;
import com.grupolemon.ocarsionplus.dto.PrecioDTO;
import com.grupolemon.ocarsionplus.dto.in.CocheDTOIn;
import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Marca;
import com.grupolemon.ocarsionplus.model.Precio;
import com.grupolemon.ocarsionplus.repository.CocheRepository;
import com.grupolemon.ocarsionplus.repository.MarcaRepository;
import com.grupolemon.ocarsionplus.repository.PrecioRepository;
import com.grupolemon.ocarsionplus.repository.specification.coche.CocheSpecification;
import com.grupolemon.ocarsionplus.service.CocheService;
import com.grupolemon.ocarsionplus.service.exceptions.CocheServiceException;
import com.grupolemon.ocarsionplus.service.exceptions.EntityNotFoundException;
import com.grupolemon.ocarsionplus.utils.ErrorConstants;
import com.grupolemon.ocarsionplus.utils.Mapper;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheRepository cocheRepository;
	@Autowired
	private PrecioRepository precioRepository;
	@Autowired
	private MarcaRepository marcaRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public CochePorFechaDTO getCar(Long idCar, LocalDate date) {

		if (null == date) {
			date = LocalDate.now();
		}

		Optional<Coche> optionalCar = cocheRepository.findById(idCar);

		if (optionalCar.isPresent()) {
			Coche car = optionalCar.get();
			CochePorFechaDTO carDTO = getCochePrecio(date, car);
			if (null != carDTO)
				return carDTO;
		}
		throw new EntityNotFoundException(ErrorConstants.COCHE_NO_ENCONTRADO);
	}

	private CochePorFechaDTO getCochePrecio(LocalDate fecha, Coche coche) {
		Optional<Precio> optionalPrecio = precioRepository.findByCocheAndFinFechaVigorAfterAndIniFechaVigorBefore(coche,
				fecha);

		if (optionalPrecio.isPresent()) {
			Precio precio = null;
			precio = optionalPrecio.get();
			return Mapper.map(coche, precio);

		}
		return null;

	}

	@Override
	public CocheFiltradoWrapperDTO get(String filtersParam) {
		Specification<Coche> specification = CocheSpecification.applySpecification(filtersParam);
		Iterable<Coche> coches = cocheRepository.findAll(specification);

		return mapeaFilteredCoches(coches);
	}

	@Override
	public CocheFiltradoWrapperDTO get() {
		Iterable<Coche> coches = cocheRepository.findAll();
		return mapeaFilteredCoches(coches);
	}

	@Override
	public long creaCoche(CocheDTOIn cocheDTOIn) throws CocheServiceException {
		compruebaCoche(cocheDTOIn);
		Coche nuevoCoche = Mapper.mapCreaCoche(cocheDTOIn);
		nuevoCoche.setMarca(buscaMarca(cocheDTOIn));
		nuevoCoche.getPrecios().add(buscaPrecio(cocheDTOIn));

		cocheRepository.save(nuevoCoche);
		return nuevoCoche.getId();
	}

	@Override
	public CocheDTO actualizaCoche(Long id, CocheDTOIn cocheDTOIn) throws CocheServiceException {

		compruebaCoche(cocheDTOIn);
		Coche cocheRecuperado = buscaCoche(id);
		cocheRecuperado = Mapper.mapActualizaCoche(cocheRecuperado, cocheDTOIn);
		cocheRecuperado.setMarca(buscaMarca(cocheDTOIn));
		cocheRepository.save(cocheRecuperado);
		return mapeaSalida(cocheRecuperado);

	}

	@Override
	public CocheDTO recuperaCoche(Long id) {
		Coche cocheRecuperado = buscaCoche(id);
		return mapeaSalida(cocheRecuperado);
	}

	@Override
	public void eliminaCoche(Long id) {
		Coche cocheRecuperado = buscaCoche(id);
		cocheRepository.delete(cocheRecuperado);
	}

	/*
	 * @Override public PrecioDTO actualizaPrecio(Long id, PrecioDTOIn precioIn)
	 * throws CocheServiceException { validaFechasDTOIn(precioIn);
	 * 
	 * Precio precio = buscaPrecio(id); return mapeaSalida(precioIn, precio);
	 * 
	 * }
	 * 
	 * @Override public void creaPrecio(PrecioDTOIn precioDTO) throws
	 * CocheServiceException { compruebaPrecio(precioDTO);
	 * validaFechasDTOIn(precioDTO); Coche coche = buscaCoche(precioDTO.getCoche());
	 * validaFechasEntidad(precioDTO, coche); Precio nuevoPrecio =
	 * mapeaCocheEnPrecio(precioDTO, coche); precioRepository.save(nuevoPrecio);
	 * 
	 * }
	 * 
	 * 
	 * @Override public void eliminaPrecio(Long id) { Precio precio =
	 * buscaPrecio(id); precioRepository.delete(precio);
	 * 
	 * }
	 * 
	 * @Override public PrecioDTO recuperaPrecio(Long id) {
	 * 
	 * Precio precio = buscaPrecio(id); return modelMapper.map(precio,
	 * PrecioDTO.class);
	 * 
	 * }
	 */
	private void compruebaCoche(CocheDTOIn cocheDTOIn) {
		if (ObjectUtils.isEmpty(cocheDTOIn)) {
			throw new IllegalArgumentException(ErrorConstants.COCHE_PARAM_VACIO);
		}
	}

	private Coche buscaCoche(Long id) {
		Optional<Coche> optionalCoche = cocheRepository.findById(id);

		if (!optionalCoche.isPresent()) {
			throw new EntityNotFoundException(ErrorConstants.COCHE_NO_ENCONTRADO);

		}
		return optionalCoche.get();

	}

	private Marca buscaMarca(CocheDTOIn cocheDTOIn) throws CocheServiceException {
		if (ObjectUtils.isEmpty(cocheDTOIn.getIdMarca())) {
			throw new IllegalArgumentException("Se debe informar un identificador de marca");

		} else {

			Optional<Marca> optionalMarca = marcaRepository.findById(cocheDTOIn.getIdMarca());
			if (!optionalMarca.isPresent()) {
				throw new IllegalArgumentException(
						"No existe marca para el id ".concat(cocheDTOIn.getIdMarca().toString()));
			}
			return optionalMarca.get();

		}
	}

	private Precio buscaPrecio(CocheDTOIn cocheDTOIn) throws CocheServiceException {
		if (!ObjectUtils.isEmpty(cocheDTOIn.getIdPrecio())) {
			Optional<Precio> optionalPrecio = precioRepository.findById(cocheDTOIn.getIdPrecio());
			if (!optionalPrecio.isPresent()) {
				throw new IllegalArgumentException(
						"No existe precio para el id ".concat(cocheDTOIn.getIdPrecio().toString()));
			}
			return optionalPrecio.get();

		}
		return null;
	}

	private CocheDTO mapeaSalida(Coche cocheRecuperado) {
		CocheDTO cocheDTO = modelMapper.map(cocheRecuperado, CocheDTO.class);
		cocheDTO.getMarca().getCoche().clear();

		String extrasPorComa = convierteListaAString(cocheRecuperado);
		cocheDTO.setExtras(extrasPorComa);
		return cocheDTO;
	}

	private String convierteListaAString(Coche cocheRecuperado) {
		List<String> extras = new ArrayList<>();
		cocheRecuperado.getExtras().forEach(thisExtra -> extras.add(thisExtra.getNombre()));
		String extrasPorComa = String.join(",", extras);
		if ("[]".equals(extrasPorComa)) {
			extrasPorComa = "";
		}
		return extrasPorComa;
	}

	private CocheFiltradoWrapperDTO mapeaFilteredCoches(Iterable<Coche> coches) {
		CocheFiltradoWrapperDTO filteredCoches = new CocheFiltradoWrapperDTO();

		for (Coche coche : coches) {

			CocheDTO unCoche = modelMapper.map(coche, CocheDTO.class);
			unCoche.getMarca().getCoche().clear();
			filteredCoches.getCoches().add(unCoche);
			unCoche.setPrecios(coche.getPrecios().stream().map(precio -> modelMapper.map(precio, PrecioDTO.class))
					.collect(Collectors.toList()));

		}
		return filteredCoches;
	}

}
