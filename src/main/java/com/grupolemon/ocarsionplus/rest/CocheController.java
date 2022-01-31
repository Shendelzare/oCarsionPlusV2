package com.grupolemon.ocarsionplus.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupolemon.ocarsionplus.dto.CocheDTO;
import com.grupolemon.ocarsionplus.dto.CochePorFechaDTO;
import com.grupolemon.ocarsionplus.dto.CocheFiltradoWrapperDTO;
import com.grupolemon.ocarsionplus.dto.in.CocheDTOIn;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.service.ApiCallService;
import com.grupolemon.ocarsionplus.service.CocheService;
import com.grupolemon.ocarsionplus.service.exceptions.CocheServiceException;

@RestController
@RequestMapping(value = "/coches")
public class CocheController {

	@Autowired
	private CocheService cocheService;
	@Autowired
	private ApiCallService apiCallService;

	@GetMapping("/{carId}/enVigor")
	public ResponseEntity<CochePorFechaDTO> getCocheEnFechaDeterminada(
			@RequestParam(name = "fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
			@PathVariable(name = "carId", required = true) Long idCar, HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.DETALLE_COCHE_EN_VIGOR, request.getRemoteAddr());
		CochePorFechaDTO car = cocheService.getCar(idCar, date);

		return new ResponseEntity<>(car, HttpStatus.OK);

	}

	@GetMapping()
	public ResponseEntity<CocheFiltradoWrapperDTO> getCar(@RequestParam(name = "filter", required = false) String filtersParam,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.LIST_CAR_FILTERED, request.getRemoteAddr());
		CocheFiltradoWrapperDTO filteredCars;

		if (ObjectUtils.isEmpty(filtersParam)) {
			filteredCars = cocheService.get();
		} else {
			filteredCars = cocheService.get(filtersParam);
		}

		return new ResponseEntity<>(filteredCars, HttpStatus.OK);

	}

	@GetMapping("/{cocheId}")
	public ResponseEntity<CocheDTO> getCoche(@PathVariable(name = "cocheId", required = true) Long idCar,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.DETALLE_COCHE, request.getRemoteAddr());
		CocheDTO car = cocheService.recuperaCoche(idCar);

		return new ResponseEntity<>(car, HttpStatus.OK);

	}

	@PutMapping("/{cocheId}")
	public ResponseEntity<CocheDTO> updateCoche(@PathVariable(name = "cocheId", required = true) Long idCoche,
			@Valid @RequestBody CocheDTOIn coche, HttpServletRequest request) throws CocheServiceException {
		apiCallService.logCall(ServicioEnum.COCHE_UPDATE, request.getRemoteAddr());
		CocheDTO cocheOut = cocheService.actualizaCoche(idCoche, coche);

		return new ResponseEntity<>(cocheOut, HttpStatus.OK);

	}

	@DeleteMapping("/{cocheId}")
	public ResponseEntity<Void> deleteCoche(@PathVariable(name = "cocheId", required = true) Long idCoche,
			HttpServletRequest request) {
		apiCallService.logCall(ServicioEnum.COCHE_DELETE, request.getRemoteAddr());
		cocheService.eliminaCoche(idCoche);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Void> createCoche(@Valid @RequestBody CocheDTOIn idCoche, HttpServletRequest request)
			throws CocheServiceException, URISyntaxException {
		apiCallService.logCall(ServicioEnum.COCHE_CREATE, request.getRemoteAddr());
		long id = cocheService.creaCoche(idCoche);

		return ResponseEntity.created(new URI(request.getRequestURI() + "/" + id)).build();

	}

}
