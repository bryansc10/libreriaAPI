package com.soca.libreriaapi.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.servicios.EditorialServicio;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {
	@Autowired
	private EditorialServicio editorialServicio;
	
	@PostMapping("/crear")
	public ResponseEntity<Object> crearEditorial(String nombre, Boolean activo) {
		try {
			editorialServicio.crearEditorial(nombre, activo);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		} catch (MiExcepcion e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
