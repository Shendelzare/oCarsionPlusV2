package com.grupolemon.ocarsionplus.utils;

import java.util.ArrayList;
import java.util.List;

import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.OperacionEnum;

public class Util {

	/**
	 * Dada una cadena , separamos por espacios y nos quedamos con el texto que nos
	 * permitirá devolver la operacion a procesar
	 * "color EQ rojo" --> EQ
	 * @param esteFiltro "color EQ rojo"
	 * @return OperacionEnum con el valor a aplicar
	 */
	public static OperacionEnum calculaOperacion(String esteFiltro) {
		for (OperacionEnum oper : OperacionEnum.values()) {
			if (oper.toString().equals(esteFiltro)) {
				return oper;
			}
		}
		return null;
	}
	
	
	public static String convierteListaAString(Coche cocheRecuperado) {
		List<String> extras = new ArrayList<>();
		cocheRecuperado.getExtras().forEach(thisExtra -> extras.add(thisExtra.getNombre()));
		return String.join(",", extras);

	}

}
