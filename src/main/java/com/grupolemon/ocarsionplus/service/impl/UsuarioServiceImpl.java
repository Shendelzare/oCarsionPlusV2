package com.grupolemon.ocarsionplus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupolemon.ocarsionplus.dto.in.UsuarioDTOIn;
import com.grupolemon.ocarsionplus.service.SsoService;
import com.grupolemon.ocarsionplus.service.UsuarioService;
import com.grupolemon.ocarsionplus.service.exceptions.UsuarioServiceException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private SsoService ssoService;

	@Override
	public void creaUsuario(UsuarioDTOIn usuario) throws UsuarioServiceException {
		ssoService.registrarUsuario(usuario.getNombre(), usuario.getContrasena(), usuario.getUsuario());
	}

}
