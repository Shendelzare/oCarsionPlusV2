package com.grupolemon.ocarsionplus.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class MarcaDTO {

	private Long id;
	private String nombre;
	@Builder.Default
	private List<CocheDTO> coche = new ArrayList<>();
}
