package com.soca.libreriaapi.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.servicios.AutorServicio;

@RestController
@RequestMapping("/autor")
public class AutorControlador {
	@Autowired
	private AutorServicio autorServicio;
	
	@PostMapping("/crear")
	public ResponseEntity<Object> crearAutor(String nombre, Boolean activo) {
		try {
			autorServicio.crearAutor(nombre, activo);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (MiExcepcion e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
