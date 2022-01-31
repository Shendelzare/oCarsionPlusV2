package com.grupolemon.ocarsionplus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="precio")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Precio {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocarsionplus_precio_id_seq" )
	@SequenceGenerator(name = "ocarsionplus_precio_id_seq", sequenceName = "ocarsionplus_precio_id_seq", allocationSize = 1 )
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name="ID_COCHE", nullable = false)
	private Coche coche;

	@Column(name="VALOR")
	private double valor;

	@Column(name="INI_FECHA_VIGOR")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate iniFechaVigor;

	@Column(name="FIN_FECHA_VIGOR")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate finFechaVigor;
	@CreatedDate
	@Column(name="FECHA_CREACION")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime fechaCreacion;
	@LastModifiedDate
	@Column(name="FECHA_MODIFICACION")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime fechaModificacion;
}
