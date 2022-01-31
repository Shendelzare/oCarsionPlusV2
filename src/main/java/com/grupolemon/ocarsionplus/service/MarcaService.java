package com.grupolemon.ocarsionplus.service;

import com.grupolemon.ocarsionplus.dto.MarcaDTO;
import com.grupolemon.ocarsionplus.dto.in.MarcaDTOIn;

public interface MarcaService {

	Long creaMarca(MarcaDTOIn marca);

	MarcaDTO actualizaMarca(Long id, MarcaDTOIn marca);

	MarcaDTO recuperaMarca(Long id);

	void eliminaMarca(Long id);

}
