package com.grupolemon.ocarsionplus.service;

import com.grupolemon.ocarsionplus.dto.in.UsuarioDTOIn;
import com.grupolemon.ocarsionplus.service.exceptions.UsuarioServiceException;

public interface UsuarioService {

	void creaUsuario(UsuarioDTOIn marca) throws UsuarioServiceException;

}
