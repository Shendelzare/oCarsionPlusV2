package com.grupolemon.ocarsionplus.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupolemon.ocarsionplus.dto.in.UsuarioDTOIn;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.service.ApiCallService;
import com.grupolemon.ocarsionplus.service.UsuarioService;
import com.grupolemon.ocarsionplus.service.exceptions.UsuarioServiceException;
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private ApiCallService apiCallService;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/register")
	public ResponseEntity<Void> createUsuario(@Valid @RequestBody UsuarioDTOIn idUsuario, HttpServletRequest request) throws UsuarioServiceException {
		apiCallService.logCall(ServicioEnum.USUARIO_CREATE, request.getRemoteAddr());
		usuarioService.creaUsuario(idUsuario);
		
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
