package com.grupolemon.ocarsionplus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.grupolemon.ocarsionplus.model.Color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(content = Include.NON_EMPTY )
public class CochePorFechaDTO {

	private Long idCoche;

	private MarcaDTO marca;

	private PrecioDTO precio;

	private Color color;
	
	private int cilindrada;
	
	private int potencia;

	private String nombreModelo;
}
