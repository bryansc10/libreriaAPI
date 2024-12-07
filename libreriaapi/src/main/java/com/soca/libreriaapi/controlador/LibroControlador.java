package com.soca.libreriaapi.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.modelos.LibroCreateDTO;
import com.soca.libreriaapi.modelos.LibroListarActivosDTO;
import com.soca.libreriaapi.servicios.LibroServicio;

@RestController
@RequestMapping("/libro")
public class LibroControlador {
	@Autowired
	private LibroServicio libroServicio;
	
	@PostMapping("/crear")
	public ResponseEntity<Object> crearLibro(@RequestBody LibroCreateDTO libroDTO) {
		try {
			libroServicio.crearLibro(libroDTO);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (MiExcepcion e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Algun dato no es correcto o es nulo, revisar.\"}");
		}
	}
	
	@GetMapping("/disponibles")
	public ResponseEntity<Object> listarLibroActivo() {
		try {
			List<LibroListarActivosDTO> librosDisponibles = libroServicio.listarLibrosActivos();
			return ResponseEntity.status(HttpStatus.OK).body(librosDisponibles);
			
		} catch (MiExcepcion e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Algun dato no es correcto o es nulo, revisar.\"}");
		}
	}
}