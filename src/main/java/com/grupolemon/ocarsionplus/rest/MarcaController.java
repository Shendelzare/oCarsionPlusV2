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

import com.grupolemon.ocarsionplus.dto.MarcaDTO;
import com.grupolemon.ocarsionplus.dto.in.MarcaDTOIn;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.service.ApiCallService;
import com.grupolemon.ocarsionplus.service.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaController {
	@Autowired
	private ApiCallService apiCallService;
	@Autowired
	private MarcaService marcaService;

	@GetMapping("/{marcaId}")
	public ResponseEntity<MarcaDTO> getMarca(@PathVariable(name = "marcaId", required = true) Long idMarca,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.MARCA_GET, request.getRemoteAddr());
				
		MarcaDTO marca = marcaService.recuperaMarca(idMarca);

		return new ResponseEntity<>(marca, HttpStatus.OK);
	}

	@PutMapping("/{marcaId}")
	public ResponseEntity<MarcaDTO> updateMarca(@PathVariable(name = "marcaId", required = true) Long idMarca,
			@Valid @RequestBody MarcaDTOIn marcaDTOIn, HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.MARCA_UPDATE, request.getRemoteAddr());
		MarcaDTO marcaOut = marcaService.actualizaMarca(idMarca, marcaDTOIn);

		return new ResponseEntity<>(marcaOut, HttpStatus.OK);

	}

	@DeleteMapping("/{marcaId}")
	public ResponseEntity<Void> deleteMarca(@PathVariable(name = "marcaId", required = true) Long idMarca,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.MARCA_DELETE, request.getRemoteAddr());
		marcaService.eliminaMarca(idMarca);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Void> createMarca(@Valid @RequestBody MarcaDTOIn marcaDTOIn, HttpServletRequest request) throws URISyntaxException {
		apiCallService.logCall(ServicioEnum.MARCA_CREATE, request.getRemoteAddr());
		Long id = marcaService.creaMarca(marcaDTOIn);
		return ResponseEntity.created(new URI(request.getRequestURI()+"/"+id)).build();

	}
}
