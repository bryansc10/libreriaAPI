package com.soca.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditorialCreateDTO {
	private String nombreEditorial;
	private Boolean actividadEditorial;
}
