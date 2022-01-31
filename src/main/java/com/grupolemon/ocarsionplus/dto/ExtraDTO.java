package com.grupolemon.ocarsionplus.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class ExtraDTO {
	@NotNull(message="nombreModelo no puede ser nulo")
	@NotBlank
	private String nombre;
}
