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

import com.grupolemon.ocarsionplus.dto.ExtraDTO;
import com.grupolemon.ocarsionplus.dto.in.ExtraDTOIn;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.service.ApiCallService;
import com.grupolemon.ocarsionplus.service.ExtraService;
import com.grupolemon.ocarsionplus.service.exceptions.ExtraServiceException;

@RestController
@RequestMapping(value = "/coches")
public class ExtraController {
	@Autowired
	private ApiCallService apiCallService;
	@Autowired
	private ExtraService extraService;

	@GetMapping("/extras/{extraId}")
	public ResponseEntity<ExtraDTO> getExtra(@PathVariable(name = "extraId", required = true) Long idExtra,
			HttpServletRequest request) throws ExtraServiceException {
		apiCallService.logCall(ServicioEnum.EXTRA_GET, request.getRemoteAddr());
		ExtraDTO extra = extraService.recuperaExtra(idExtra);

		return new ResponseEntity<>(extra, HttpStatus.OK);
	}

	@PutMapping("/extras/{extraId}")
	public ResponseEntity<ExtraDTO> updateExtra(@PathVariable(name = "extraId", required = true) Long idExtra,
			@Valid @RequestBody ExtraDTOIn extra, HttpServletRequest request) throws ExtraServiceException {
		apiCallService.logCall(ServicioEnum.EXTRA_UPDATE, request.getRemoteAddr());
		ExtraDTO extraOut = extraService.actualizaExtra(idExtra, extra);

		return new ResponseEntity<>(extraOut, HttpStatus.OK);

	}

	@DeleteMapping("/extras/{extraId}")
	public ResponseEntity<Void> deleteExtra(@PathVariable(name = "extraId", required = true) Long idExtra,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.EXTRA_DELETE, request.getRemoteAddr());
		extraService.eliminaExtra(idExtra);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/{cocheId}/extras")
	public ResponseEntity<Void> createExtra(@PathVariable(name = "cocheId", required = true) Long cocheId,
			@Valid @RequestBody ExtraDTOIn extraDTO, HttpServletRequest request)
			throws ExtraServiceException, URISyntaxException {
		apiCallService.logCall(ServicioEnum.EXTRA_CREATE, request.getRemoteAddr());
		Long id = extraService.creaExtra(cocheId,extraDTO);
		return ResponseEntity.created(new URI(request.getRequestURI()+"/"+id)).build();

	}
}
