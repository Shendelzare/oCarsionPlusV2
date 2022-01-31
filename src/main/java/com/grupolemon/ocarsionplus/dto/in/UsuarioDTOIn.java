package com.grupolemon.ocarsionplus.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NonNull
public class UsuarioDTOIn {

	private String nombre;
	
	private String contrasena;
	
	private String usuario;
	

}
