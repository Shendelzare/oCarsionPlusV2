package com.grupolemon.ocarsionplus.repository.specification.coche;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Color;
import com.grupolemon.ocarsionplus.model.CondicionEnum;
import com.grupolemon.ocarsionplus.model.OperacionEnum;
import com.grupolemon.ocarsionplus.utils.Constantes;

public final class CocheSpecification {
	private static final Logger LOG = LogManager.getLogger(CocheSpecification.class);

	private CocheSpecification() {
	}

	public static Specification<Coche> applySpecification(String filter) {
		String[] listaFiltros = filter.split(Constantes.SEPARADOR_FILTROS);

		boolean isEspecificacionInicializada = false;
		OperacionEnum operacionActual = null;
		CondicionEnum condicionActual = null;
		Specification<Coche> especificacion = null;
		for (String esteFiltro : listaFiltros) {

			LOG.debug(esteFiltro);
			String[] textoSplitted = esteFiltro.split(" ");
			compruebaTextoDividido(textoSplitted);
			operacionActual = OperacionEnum.valueOf(textoSplitted[1].toUpperCase());
			condicionActual = CondicionEnum.valueOf(textoSplitted[0].toUpperCase());

			if (isEspecificacionInicializada) {
				especificacion = especificacion
						.and(creaEspecificacion(condicionActual, operacionActual, textoSplitted[2]));
			} else {
				especificacion = Specification
						.where(creaEspecificacion(condicionActual, operacionActual, textoSplitted[2]));
				isEspecificacionInicializada = true;
			}
		}
		return especificacion;

	}

	private static void compruebaTextoDividido(String[] textoSplitted) {
		if(textoSplitted.length<3 || textoSplitted.length>3) {
			throw new IllegalArgumentException("filtro malformado");
		}
		
	}

	private static Specification<Coche> creaEspecificacion(CondicionEnum condicionActual, OperacionEnum operacionActual,
			String valor) {

		String valorUppercase = valor.toUpperCase();

		switch (condicionActual) {
		case CILINDRADA:
		case POTENCIA:
			return aplicaSpecificationNumerica(condicionActual.name().toLowerCase(), operacionActual, valorUppercase);
		case COLOR:
			return aplicaSpecificationColor(operacionActual, valorUppercase);
		case MARCA:
			return aplicaSpecificationMarca(operacionActual, valorUppercase);
		default:
			throw new IllegalArgumentException();
		}

	}


	private static Specification<Coche> aplicaSpecificationNumerica(String columna, OperacionEnum operacionActual,
			String valorUppercase) {
		switch (operacionActual) {
		case EQ:
			return (root, query, builder) -> builder.equal(root.<String>get(columna), valorUppercase);
		case GT:
			return (root, query, builder) -> builder.greaterThanOrEqualTo(root.<String>get(columna), valorUppercase);
		case LT:
			return (root, query, builder) -> builder.lessThanOrEqualTo(root.<String>get(columna), valorUppercase);
		default:
			throw new IllegalArgumentException();
		}
	}

	private static Specification<Coche> aplicaSpecificationColor(OperacionEnum operacionActual, String valor) {
		switch (operacionActual) {
		case EQ:
			return (root, query, builder) -> builder.equal(root.<Long>get("color"), Color.valueOf(valor.toUpperCase()));
		case NE:
			return (root, query, builder) -> builder.notEqual(root.<Long>get("color"), Color.valueOf(valor.toUpperCase()));
		default:
			throw new IllegalArgumentException();
		}
	}

	private static Specification<Coche> aplicaSpecificationMarca(OperacionEnum operacionActual, String valorUppercase) {
		switch (operacionActual) {
		case EQ:
			return (root, query, builder) -> builder.equal(builder.upper(root.join("marca").<String>get("nombre")),
					valorUppercase.toUpperCase());

		case NE:
			return (root, query, builder) -> builder.notEqual(builder.upper(root.join("marca").<String>get("nombre")),
					valorUppercase);
		default:
			throw new IllegalArgumentException();
		}
	}
}
