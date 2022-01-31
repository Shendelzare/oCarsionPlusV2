package com.grupolemon.ocarsionplus.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.grupolemon.ocarsionplus.model.Color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class CocheDTO {

	private Long idCoche;

	private MarcaDTO marca;

	@Builder.Default
	private List<PrecioDTO> precios = new ArrayList<>();
	
	private String extras;
	
	private Color color;
	
	private int cilindrada;
	
	private int potencia;
	
	private String nombreModelo;

}
