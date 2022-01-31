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
@JsonInclude(content = Include.NON_EMPTY)
public class CocheFiltradoWrapperDTO {
	@Getter
	@Setter
	private List<CocheDTO> coches = new ArrayList<>();

}
