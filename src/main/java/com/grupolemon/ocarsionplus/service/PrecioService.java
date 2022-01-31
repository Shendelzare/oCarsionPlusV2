package com.grupolemon.ocarsionplus.service;

import com.grupolemon.ocarsionplus.dto.PrecioDTO;
import com.grupolemon.ocarsionplus.dto.in.PrecioDTOIn;
import com.grupolemon.ocarsionplus.service.exceptions.PrecioServiceException;

public interface PrecioService {

	Long creaPrecio(PrecioDTOIn precio) throws PrecioServiceException;

	PrecioDTO actualizaPrecio(Long idPrecio, PrecioDTOIn precio) throws PrecioServiceException;

	PrecioDTO recuperaPrecio(Long idPrecio);

	void eliminaPrecio(Long idPrecio);

}
