package com.grupolemon.ocarsionplus.service;

import java.time.LocalDate;

import com.grupolemon.ocarsionplus.dto.CocheDTO;
import com.grupolemon.ocarsionplus.dto.CochePorFechaDTO;
import com.grupolemon.ocarsionplus.dto.CocheFiltradoWrapperDTO;
import com.grupolemon.ocarsionplus.dto.in.CocheDTOIn;
import com.grupolemon.ocarsionplus.service.exceptions.CocheServiceException;

public interface CocheService {

	public CochePorFechaDTO getCar(Long idCar, LocalDate date);

	public CocheFiltradoWrapperDTO get();

	public CocheFiltradoWrapperDTO get(String filtersParam);

	long creaCoche(CocheDTOIn idCoche) throws CocheServiceException;

	CocheDTO actualizaCoche(Long idCoche, CocheDTOIn coche) throws CocheServiceException;

	CocheDTO recuperaCoche(Long idCoche);

	void eliminaCoche(Long idCoche);

}
