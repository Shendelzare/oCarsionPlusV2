package com.grupolemon.ocarsionplus.dto.in;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
@NotNull
public class PrecioDTOIn {
	@NotNull(message ="valor no puede ser nulo")
	@Min(message="Valor no puede ser inferior a 1", value = 1)
	private Double valor;
	@NotNull(message ="iniFechaVigor no puede ser nulo")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate iniFechaVigor;
	@NotNull(message ="finFechaVigor no puede ser nulo")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate finFechaVigor;
	
	@NotNull(message ="coche no puede ser nulo")
	private Long coche;
}
