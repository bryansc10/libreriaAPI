package com.soca.libreriaapi.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libro")
@NoArgsConstructor
@Getter
@Setter
public class Libro {
	@Id
	private Integer id_libro;
	@Column(nullable = false)
	private Integer ejemplares;
	@Column(nullable = false)
	private Boolean libro_activo;
	@Column(nullable = false)
	private String titulo;
	@ManyToOne
	@JoinColumn(name = "id_autor", nullable = false)
	private Autor id_autor;
	@ManyToOne
	@JoinColumn(name = "id_editorial", nullable = false)
	private Editorial id_editorial;
}
