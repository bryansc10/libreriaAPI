package com.soca.libreriaapi.modelos;

import lombok.Data;

@Data
public class LibroCreateDTO {
	private Integer isbn;
	private String titulo;
	private Integer ejemplares;
	private String idAutor;
	private String idEditorial;
	private Boolean libroActivo;
}
