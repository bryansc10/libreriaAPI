package com.soca.libreriaapi.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soca.libreriaapi.entidades.Autor;
import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.modelos.AutorModificarDTO;
import com.soca.libreriaapi.servicios.AutorServicio;

@RestController
@RequestMapping("/autor")
public class AutorControlador {
	@Autowired
	private AutorServicio autorServicio;
	
	// POST
	@PostMapping("/crear")
	public ResponseEntity<Object> crearAutor(@RequestParam String nombre, @RequestParam Boolean activo) {
		try {
			autorServicio.crearAutor(nombre, activo);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		} catch (MiExcepcion e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// GET
	@GetMapping("/listar")
	public ResponseEntity<Object> listarAutores() {
		try {
			List<Autor> lista = autorServicio.listarAutores();
			return ResponseEntity.ok().body(lista);
			
		} catch (MiExcepcion e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	// PATCH
	@PatchMapping("/modificar")
	public ResponseEntity<Void> modificarAutor(@RequestBody AutorModificarDTO modificar) {
		try {
			autorServicio.cambiarNombre(modificar);
			return ResponseEntity.ok().build();
			
		} catch (MiExcepcion e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
