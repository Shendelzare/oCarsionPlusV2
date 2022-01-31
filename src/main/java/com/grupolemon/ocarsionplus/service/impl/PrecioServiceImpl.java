package com.grupolemon.ocarsionplus.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.grupolemon.ocarsionplus.dto.PrecioDTO;
import com.grupolemon.ocarsionplus.dto.in.PrecioDTOIn;
import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Precio;
import com.grupolemon.ocarsionplus.repository.CocheRepository;
import com.grupolemon.ocarsionplus.repository.PrecioRepository;
import com.grupolemon.ocarsionplus.service.PrecioService;
import com.grupolemon.ocarsionplus.service.exceptions.PrecioServiceException;
import com.grupolemon.ocarsionplus.utils.ErrorConstants;

@Service
public class PrecioServiceImpl implements PrecioService {
	@Autowired
	private PrecioRepository precioRepository;

	@Autowired
	private CocheRepository cocheRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public PrecioDTO actualizaPrecio(Long id, PrecioDTOIn precioIn) throws PrecioServiceException {
		validaFechasDTOIn(precioIn);
		Coche coche = buscaCoche(precioIn.getCoche());
		validaFechasEntidad(precioIn, coche);
		Precio precio = buscaPrecio(id);
		mapeaPrecio(precioIn, precio);
		precio.setCoche(coche);
		precioRepository.save(precio);
		return mapeaSalida(precio);

	}

	private void mapeaPrecio(PrecioDTOIn precioIn, Precio precio) {
		modelMapper.map(precioIn, precio);

	}

	@Override
	public Long creaPrecio(PrecioDTOIn precioDTO) throws PrecioServiceException {
		compruebaPrecio(precioDTO);
		validaFechasDTOIn(precioDTO);
		Coche coche = buscaCoche(precioDTO.getCoche());
		validaFechasEntidad(precioDTO, coche);
		Precio nuevoPrecio = mapeaCocheEnPrecio(precioDTO, coche);
		precioRepository.save(nuevoPrecio);
		return nuevoPrecio.getId();
	}

	@Override
	public void eliminaPrecio(Long id) {
		Precio precio = buscaPrecio(id);
		precioRepository.delete(precio);

	}

	@Override
	public PrecioDTO recuperaPrecio(Long id) {

		Precio precio = buscaPrecio(id);
		return modelMapper.map(precio, PrecioDTO.class);

	}

	private Coche buscaCoche(Long id) {
		Optional<Coche> optionalCoche = cocheRepository.findById(id);

		if (!optionalCoche.isPresent()) {
			throw new EntityNotFoundException(ErrorConstants.COCHE_NO_ENCONTRADO);

		}
		return optionalCoche.get();

	}

	private Precio buscaPrecio(Long id) {

		Optional<Precio> optionalPrecio = precioRepository.findById(id);
		if (!optionalPrecio.isPresent()) {
			throw new EntityNotFoundException("No existe precio para el id ".concat(id.toString()));
		}
		return optionalPrecio.get();

	}

	private void compruebaPrecio(PrecioDTOIn precioDTO) {
		if (ObjectUtils.isEmpty(precioDTO)) {
			throw new IllegalArgumentException(ErrorConstants.PRECIO_PARAM_VACIO);
		}

	}

	private Precio mapeaCocheEnPrecio(PrecioDTOIn precioDTO, Coche coche) {
		Precio nuevoPrecio = modelMapper.map(precioDTO, Precio.class);
		nuevoPrecio.setCoche(coche);
		nuevoPrecio.setId(null);
		return nuevoPrecio;
	}

	private PrecioDTO mapeaSalida(Precio precio) {

		return modelMapper.map(precio, PrecioDTO.class);
	}

	private void validaFechasDTOIn(PrecioDTOIn precioDTO) {

		if (precioDTO.getIniFechaVigor().isAfter(precioDTO.getFinFechaVigor())) {
			throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la de fin");
		}

	}

	private void validaFechasEntidad(PrecioDTOIn precioDTO, Coche coche) {
		List<Precio> preciosActuales = coche.getPrecios();

		boolean noEsFechaValida = preciosActuales.stream()
				.anyMatch(precio -> precio.getFinFechaVigor().isAfter(precioDTO.getIniFechaVigor()));
		if (noEsFechaValida) {
			throw new IllegalArgumentException(
					"La fecha de inicio debe ser posterior a la ultima fecha de vencimiento existenet");
		}
	}
}
