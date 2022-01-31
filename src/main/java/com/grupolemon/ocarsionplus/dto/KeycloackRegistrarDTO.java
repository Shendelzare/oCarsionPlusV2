package com.grupolemon.ocarsionplus.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KeycloackRegistrarDTO implements Serializable{

	private static final long serialVersionUID = 7222121666950901479L;
	private String firstName;
	private String password;
	private String username;
}
