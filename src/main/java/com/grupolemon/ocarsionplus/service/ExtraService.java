package com.grupolemon.ocarsionplus.service;

import javax.validation.Valid;

import com.grupolemon.ocarsionplus.dto.ExtraDTO;
import com.grupolemon.ocarsionplus.dto.in.ExtraDTOIn;
import com.grupolemon.ocarsionplus.service.exceptions.ExtraServiceException;

public interface ExtraService {

	ExtraDTO recuperaExtra(Long idExtra) throws ExtraServiceException;

	ExtraDTO actualizaExtra(Long idExtra, @Valid ExtraDTOIn extra) throws ExtraServiceException;

	void eliminaExtra(Long idExtra);

	Long creaExtra(Long cocheId, @Valid ExtraDTOIn extraDTOIn);

}
