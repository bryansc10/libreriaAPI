package com.soca.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibrosPorEntidadDTO {
	private String titulo;
	private String autorNombre;
	private String editorialNombre;
	private Boolean libro_activo;
	private Integer ejemplares;
}
