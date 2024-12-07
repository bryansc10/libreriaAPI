package com.soca.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibroListarActivosDTO {
	private String titulo;
	private Integer ejemplares;
}