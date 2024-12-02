package com.soca.libreriaapi.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "editorial")
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Editorial {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(length = 16)
	private String id_editorial;
	@Column(length = 1, nullable = false)
	private Boolean editorial_activa;
	@Column(length = 255, nullable = false)
	private String nombre_editorial;
}
