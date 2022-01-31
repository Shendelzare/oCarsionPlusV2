package com.grupolemon.ocarsionplus.utils;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;

import com.grupolemon.ocarsionplus.dto.CochePorFechaDTO;
import com.grupolemon.ocarsionplus.dto.MarcaDTO;
import com.grupolemon.ocarsionplus.dto.PrecioDTO;
import com.grupolemon.ocarsionplus.dto.in.CocheDTOIn;
import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Precio;

public final class Mapper {

	private Mapper() {

	}

	public static CochePorFechaDTO map(Coche car, Precio price) {
		CochePorFechaDTO carDTO = new CochePorFechaDTO();
		carDTO.setMarca(new ModelMapper().map(car.getMarca(), MarcaDTO.class));
		carDTO.setIdCoche(car.getId());
		carDTO.setColor(car.getColor());
		PrecioDTO priceDTO = new PrecioDTO();
		priceDTO.setIniFechaVigor(price.getIniFechaVigor());
		priceDTO.setFinFechaVigor(price.getFinFechaVigor());
		priceDTO.setValor(price.getValor());
		priceDTO.setId(price.getId());
		carDTO.setPrecio(priceDTO);
		return carDTO;
	}

	public static Coche mapCreaCoche(CocheDTOIn cocheDTOIn) {
		Coche nuevoCoche = new Coche();
		nuevoCoche.setCilindrada(cocheDTOIn.getCilindrada());
		nuevoCoche.setColor(cocheDTOIn.getColor());
		nuevoCoche.setMarca(null);
		nuevoCoche.setPrecios(new ArrayList<>());
		nuevoCoche.setExtras(new ArrayList<>());
		nuevoCoche.setPotencia(cocheDTOIn.getPotencia());
		nuevoCoche.setNombreModelo(cocheDTOIn.getNombreModelo());
		return nuevoCoche;
	}

	public static Coche mapActualizaCoche(Coche coche, CocheDTOIn cocheDTOIn) {
		if (cocheDTOIn.getCilindrada() != null) {
			coche.setCilindrada(cocheDTOIn.getCilindrada());
		}
		if (cocheDTOIn.getColor() != null) {
			coche.setColor(cocheDTOIn.getColor());
		}
		if (cocheDTOIn.getPotencia() != null) {
			coche.setPotencia(cocheDTOIn.getPotencia());
		}
		if (cocheDTOIn.getNombreModelo() != null) {
			coche.setNombreModelo(cocheDTOIn.getNombreModelo());
		}
		return coche;

	}

}
