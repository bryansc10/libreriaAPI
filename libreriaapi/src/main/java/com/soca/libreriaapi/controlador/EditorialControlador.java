package com.soca.libreriaapi.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soca.libreriaapi.entidades.Editorial;
import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.modelos.EditorialCreateDTO;
import com.soca.libreriaapi.servicios.EditorialServicio;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {
	@Autowired
	private EditorialServicio editorialServicio;
	
	@GetMapping("/listarEditorial/{id}")
	public ResponseEntity<Editorial> listarEditorial(@PathVariable("id") String id) {
		try {
            Editorial editorial = editorialServicio.obtenerEditorial(id);
            return ResponseEntity.ok(editorial);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@PostMapping("/crearP")
	public ResponseEntity<Object> crearEditorial(@RequestParam String nombre, @RequestParam Boolean activo) {
		try {
			editorialServicio.crearEditorial(nombre, activo);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		} catch (MiExcepcion e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Object> crearEditorial(@RequestBody EditorialCreateDTO editorialDTO) {
		try {
			editorialServicio.crearEditorial(editorialDTO);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		} catch (MiExcepcion e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
