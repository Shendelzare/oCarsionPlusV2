package com.grupolemon.ocarsionplus.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.grupolemon.ocarsionplus.dto.CocheDTO;
import com.grupolemon.ocarsionplus.dto.MarcaDTO;
import com.grupolemon.ocarsionplus.dto.in.MarcaDTOIn;
import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Marca;
import com.grupolemon.ocarsionplus.repository.MarcaRepository;
import com.grupolemon.ocarsionplus.service.MarcaService;
import com.grupolemon.ocarsionplus.utils.Util;

@Service
public class MarcaServiceImpl implements MarcaService {
	@Autowired
	private MarcaRepository marcaRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Long creaMarca(MarcaDTOIn marcaIn) {

		compruebaMarca(marcaIn);
		Marca nuevaMarca = mapeaMarcaDTOIn(marcaIn);
		marcaRepository.save(nuevaMarca);
		return nuevaMarca.getId();

	}

	private Marca mapeaMarcaDTOIn(MarcaDTOIn marcaIn) {
		return modelMapper.map(marcaIn, Marca.class);
	}

	@Override
	public MarcaDTO actualizaMarca(Long id, MarcaDTOIn marcaIn) {
		compruebaMarca(marcaIn);

		Marca marca = buscaMarca(id);
		mapeaMarcaDTOIn(marcaIn, marca);

		Marca marcaUpdated = marcaRepository.save(marca);
		return modelMapper.map(marcaUpdated, MarcaDTO.class);
	}

	private Marca buscaMarca(Long id) {
		Optional<Marca> estaMarca = marcaRepository.findById(id);
		if (estaMarca.isPresent()) {
			return estaMarca.get();
		} else {
			throw new EntityNotFoundException();

		}
	}

	@Override
	public MarcaDTO recuperaMarca(Long id) {
		Marca marca = buscaMarca(id);
		return mapeaMarca(marca);

	}

	@Override
	public void eliminaMarca(Long id) {

		Marca marca = buscaMarca(id);
		marcaRepository.delete(marca);

	}

	private void compruebaMarca(MarcaDTOIn marca) {
		if (ObjectUtils.isEmpty(marca) || ObjectUtils.isEmpty(marca.getNombre()))
			throw new IllegalArgumentException("Debe informarse un nombre de marca");
	}

	private void mapeaMarcaDTOIn(MarcaDTOIn marcaIn, Marca marca) {

		modelMapper.map(marcaIn, marca);
	}

	private MarcaDTO mapeaMarca(Marca marcaIn) {
		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setNombre(marcaIn.getNombre());
		marcaDTO.setId(marcaIn.getId());
		for (Coche esteCoche : marcaIn.getCoche()) {
			CocheDTO cocheDTO = modelMapper.map(esteCoche, CocheDTO.class);
			cocheDTO.setExtras(Util.convierteListaAString(esteCoche));
			cocheDTO.setMarca(null);
			marcaDTO.getCoche().add(cocheDTO);
		}

		return marcaDTO;

	}
}
