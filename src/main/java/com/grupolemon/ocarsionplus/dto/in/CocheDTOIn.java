package com.grupolemon.ocarsionplus.dto.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.grupolemon.ocarsionplus.model.Color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CocheDTOIn {
	@NotNull(message="nombreModelo no puede ser nulo")
	@NotBlank
	private String nombreModelo;
	@NotNull(message="cilindrada no puede ser nulo")
	@Min(0)
	private Integer cilindrada;
	@NotNull(message="potencia no puede ser nulo")
	@Min(0)
	private Integer potencia;
	@NotNull(message="idMarca no puede ser nulo")
	private Long idMarca;
	private Long idPrecio;
	@NotNull(message="Color no puede ser nulo")
	private Color color;

}
