package com.grupolemon.ocarsionplus.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="coche")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Coche {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocarsionplus_coche_id_seq" )
	@SequenceGenerator(name = "ocarsionplus_coche_id_seq", sequenceName = "ocarsionplus_coche_id_seq", allocationSize = 1 )
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MARCA", nullable = false)
	private Marca marca;

	@OneToMany(mappedBy = "coche",cascade = CascadeType.REMOVE)
	private List<Precio> precios;
	
	@OneToMany(mappedBy = "coche",cascade = CascadeType.REMOVE)
	private List<Extra> extras;

	@Column(name = "NOMBRE_MODELO")
	private String nombreModelo;
	
	@Enumerated(EnumType.ORDINAL)
	private Color color;

	@Column(name = "CILINDRADA")
	private int cilindrada;

	@Column(name = "POTENCIA")
	private int potencia;
	
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
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "ID_USUARIO_CREADOR", nullable = false) private Usuario
	 * creador;
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "ID_USUARIO_EDITOR", nullable = false) private Usuario
	 * editor;
	 */

}
