package com.grupolemon.ocarsionplus.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@NonNull
@Getter
@Setter
public class MarcaDTOIn {
	
	@NonNull
	private String nombre;

}
