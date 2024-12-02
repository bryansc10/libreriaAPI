package com.soca.libreriaapi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soca.libreriaapi.entidades.Libro;
import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.repositorios.AutorRepositorio;
import com.soca.libreriaapi.repositorios.EditorialRepositorio;
import com.soca.libreriaapi.repositorios.LibroRepositorio;

@Service
public class LibroServicio {
	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private AutorRepositorio autorRepositorio;
	@Autowired
	private EditorialRepositorio editorialRepositorio;

	// CREATE
	@Transactional
	public void crearLibro(Integer id_libro, 
						   Integer ejemplares, 
						   Boolean libro_activo, 
						   String titulo, 
						   String id_titulo,
						   String id_editorial, 
						   String id_autor) throws MiExcepcion {
		validar(id_libro, ejemplares, id_titulo, id_autor, id_editorial);
		Libro libro = new Libro();
		libro.setId_libro(id_libro);
		libro.setEjemplares(ejemplares);
		libro.setLibro_activo(libro_activo);
		libro.setTitulo(id_titulo);
		libro.setId_editorial(editorialRepositorio.getReferenceById(id_editorial));
		libro.setId_autor(autorRepositorio.getReferenceById(id_autor));
		
		libroRepositorio.save(libro);
	}
	
	// READ
	@Transactional(readOnly = true)
	public List<Libro> listarLibros() {
		return libroRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Libro getOne(Integer id_libro) {
		return libroRepositorio.getReferenceById(id_libro);
	}
	
	// UPDATE
		public void modificarLibro(Integer id_libro, 
							   		Integer ejemplares, 
							   		Boolean libro_activo, 
							   		String titulo, 
							   		String id_titulo,
							   		String id_editorial, 
							   		String id_autor) throws MiExcepcion {
			validar(id_libro, ejemplares, id_titulo, id_autor, id_editorial);
			Libro libro = libroRepositorio.findById(id_libro).get();
			if (libro == null) {
				throw new MiExcepcion("No se encontró libro con id: " + id_libro);
			}
			libro.setId_libro(id_libro);
			libro.setEjemplares(ejemplares);
			libro.setLibro_activo(libro_activo);
			libro.setTitulo(id_titulo);
			libro.setId_editorial(editorialRepositorio.getReferenceById(id_editorial));
			libro.setId_autor(autorRepositorio.getReferenceById(id_autor));
			
			libroRepositorio.save(libro);
		}

	// DELETE
	@Transactional
	public void excluirLibro(Integer id_libro) throws MiExcepcion {
		Libro libro = libroRepositorio.getReferenceById(id_libro);
		libro.setLibro_activo(false);
	}
	
	// VALIDAR
	private void validar(Integer id_libro, Integer ejemplares, String titulo, String id_autor, String id_editorial)
			throws MiExcepcion {
		if (id_libro == null) {
			throw new MiExcepcion("El codigo isbn no puede ser nulo.");
		}
		if (titulo.isEmpty() || titulo == null) {
			throw new MiExcepcion("El título ingresado no puede ser nulo o vacío.");
		}
		if (ejemplares == null) {
			throw new MiExcepcion("La cantidad de ejemplares ingresado no puede ser nulo.");
		}
		if (id_autor.isEmpty() || id_autor == null || autorRepositorio.findById(id_autor).isEmpty()) {
			throw new MiExcepcion("El id de autor ingresado es nulo o no existe en la base de datos.");
		}
		if (id_editorial.isEmpty() || id_editorial == null || editorialRepositorio.findById(id_editorial).isEmpty()) {
			throw new MiExcepcion("El id de editorial ingresado es nulo o no existe en la base de datos.");
		}
	}
}
