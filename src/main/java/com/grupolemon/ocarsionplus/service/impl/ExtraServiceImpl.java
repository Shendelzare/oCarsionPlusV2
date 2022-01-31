package com.grupolemon.ocarsionplus.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.grupolemon.ocarsionplus.dto.ExtraDTO;
import com.grupolemon.ocarsionplus.dto.in.ExtraDTOIn;
import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Extra;
import com.grupolemon.ocarsionplus.repository.CocheRepository;
import com.grupolemon.ocarsionplus.repository.ExtraRepository;
import com.grupolemon.ocarsionplus.service.ExtraService;
import com.grupolemon.ocarsionplus.service.exceptions.EntityNotFoundException;
import com.grupolemon.ocarsionplus.service.exceptions.ExtraServiceException;
import com.grupolemon.ocarsionplus.utils.ErrorConstants;

@Service
public class ExtraServiceImpl implements ExtraService {

	@Autowired
	private ExtraRepository extraRepository;
	@Autowired
	private CocheRepository cocheRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public ExtraDTO actualizaExtra(Long id, ExtraDTOIn extraDTOIn) throws ExtraServiceException {
		compruebaExtra(extraDTOIn);
		Extra extra = buscaExtra(id);
		mapeaExtraEntrada(extraDTOIn, extra);
		extraRepository.save(extra);
		return mapeaExtraSalida(extra);
	}

	@Override
	public Long creaExtra(Long cocheId, ExtraDTOIn extraDTOIn) {
		compruebaExtra(extraDTOIn);
		Coche coche = buscaCoche(cocheId);
		Extra nuevoExtra = mapeaCocheEnExtra(extraDTOIn, coche);

		extraRepository.save(nuevoExtra);
		return nuevoExtra.getId();
	}

	@Override
	public void eliminaExtra(Long id) {
		Extra extra = buscaExtra(id);
		extraRepository.delete(extra);

	}

	@Override
	public ExtraDTO recuperaExtra(Long id) throws ExtraServiceException {
		Extra extra = buscaExtra(id);
		return mapeaExtraSalida(extra);
	}

	private Coche buscaCoche(Long id) {
		Optional<Coche> optionalCoche = cocheRepository.findById(id);

		if (!optionalCoche.isPresent()) {
			throw new EntityNotFoundException(ErrorConstants.COCHE_NO_ENCONTRADO);

		}
		return optionalCoche.get();

	}

	private Extra buscaExtra(Long id) {
		Optional<Extra> optionalExtra = extraRepository.findById(id);
		if (!optionalExtra.isPresent()) {
			throw new EntityNotFoundException("No existe extra para el id ".concat(id.toString()));
		}
		return optionalExtra.get();

	}

	private void compruebaExtra(ExtraDTOIn extraDTOIn) {
		if (ObjectUtils.isEmpty(extraDTOIn)) {
			throw new IllegalArgumentException(ErrorConstants.EXTRA_PARAM_VACIO);
		}
	}

	private Extra mapeaCocheEnExtra(ExtraDTOIn extraDTOIn, Coche coche) {
		Extra nuevoExtra = modelMapper.map(extraDTOIn, Extra.class);
		nuevoExtra.setCoche(coche);
		nuevoExtra.setId(null);
		return nuevoExtra;
	}

	private void mapeaExtraEntrada(ExtraDTOIn extraDTOIn, Extra extra) {

		modelMapper.map(extraDTOIn, extra);

	}

	private ExtraDTO mapeaExtraSalida(Extra extra) {
		ExtraDTO extraDTO = modelMapper.map(extra, ExtraDTO.class);
		if (null != extraDTO)
			return extraDTO;
		return extraDTO;
	}

}
