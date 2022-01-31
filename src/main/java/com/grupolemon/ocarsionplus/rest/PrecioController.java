package com.grupolemon.ocarsionplus.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupolemon.ocarsionplus.dto.PrecioDTO;
import com.grupolemon.ocarsionplus.dto.in.PrecioDTOIn;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.service.ApiCallService;
import com.grupolemon.ocarsionplus.service.PrecioService;
import com.grupolemon.ocarsionplus.service.exceptions.PrecioServiceException;

@RestController
@RequestMapping(value = "/coches")
public class PrecioController {
	@Autowired
	private ApiCallService apiCallService;
	@Autowired
	private PrecioService precioService;

	@GetMapping("/precios/{precioId}")
	public ResponseEntity<PrecioDTO> getPrecio(@PathVariable(name = "precioId", required = true) Long idPrecio,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.PRECIO_GET, request.getRemoteAddr());
		PrecioDTO precio = precioService.recuperaPrecio(idPrecio);

		return new ResponseEntity<>(precio, HttpStatus.OK);
	}

	@PutMapping("/precios/{precioId}")
	public ResponseEntity<PrecioDTO> updatePrecio(@PathVariable(name = "precioId", required = true) Long idPrecio,
			@Valid @RequestBody PrecioDTOIn precio, HttpServletRequest request) throws PrecioServiceException {
		apiCallService.logCall(ServicioEnum.PRECIO_UPDATE, request.getRemoteAddr());
		PrecioDTO precioOut = precioService.actualizaPrecio(idPrecio, precio);

		return new ResponseEntity<>(precioOut, HttpStatus.OK);

	}

	@DeleteMapping("/precios/{precioId}")
	public ResponseEntity<Void> deletePrecio(@PathVariable(name = "precioId", required = true) Long idPrecio,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.PRECIO_DELETE, request.getRemoteAddr());
		precioService.eliminaPrecio(idPrecio);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/precios")
	public ResponseEntity<Void> createPrecio(@Valid @RequestBody PrecioDTOIn precioDTO, HttpServletRequest request)
			throws PrecioServiceException, URISyntaxException {
		apiCallService.logCall(ServicioEnum.PRECIO_CREATE, request.getRemoteAddr());
		Long id = precioService.creaPrecio(precioDTO);
		return ResponseEntity.created(new URI(request.getRequestURI() + "/" + id)).build();

	}
}
