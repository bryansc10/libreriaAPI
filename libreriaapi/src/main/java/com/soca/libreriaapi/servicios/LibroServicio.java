package com.soca.libreriaapi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soca.libreriaapi.entidades.Libro;
import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.modelos.LibroCreateDTO;
import com.soca.libreriaapi.modelos.LibroListarActivosDTO;
import com.soca.libreriaapi.modelos.LibrosPorEntidadDTO;
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
	public void crearLibro(LibroCreateDTO libroCreateDTO) throws MiExcepcion {
		validar(libroCreateDTO);
		Libro libro = new Libro();
		libro.setId_libro(libroCreateDTO.getIsbn());
		libro.setEjemplares(libroCreateDTO.getEjemplares());
		libro.setLibro_activo(libroCreateDTO.getLibroActivo());
		libro.setTitulo(libroCreateDTO.getTitulo());
		libro.setId_editorial(editorialRepositorio.getReferenceById(libroCreateDTO.getIdEditorial()));
		libro.setId_autor(autorRepositorio.getReferenceById(libroCreateDTO.getIdAutor()));
		
		libroRepositorio.save(libro);
	}
	
	// READ
	@Transactional(readOnly = true)
	public List<Libro> listarLibros() throws MiExcepcion {
		return libroRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Libro getOne(Integer id_libro) throws MiExcepcion {
		return libroRepositorio.getReferenceById(id_libro);
	}
	
	@Transactional(readOnly = true)
	public List<LibroListarActivosDTO> listarLibrosActivos() throws MiExcepcion {
		return libroRepositorio.encontrarActivos();
	}
	
	@Transactional(readOnly = true)
	public List<LibrosPorEntidadDTO> listarLibrosIDEditorial(String idEditorial) throws MiExcepcion {
		return libroRepositorio.listarLibrosPorEditorial(idEditorial);
	}
	
	@Transactional(readOnly = true)
	public List<LibrosPorEntidadDTO> listarLibrosIDAutor(String idAutor) throws MiExcepcion {
		return libroRepositorio.listarLibrosPorAutor(idAutor);
	}
	
	// UPDATE
		public void modificarLibro(LibroCreateDTO libroCreateDTO) throws MiExcepcion {
			validar(libroCreateDTO);
			Libro libro = libroRepositorio.findById(libroCreateDTO.getIsbn()).get();
			if (libro == null) {
				throw new MiExcepcion("No se encontró libro con id: " + libroCreateDTO.getIsbn());
			}
			libro.setId_libro(libroCreateDTO.getIsbn());
			libro.setEjemplares(libroCreateDTO.getEjemplares());
			libro.setLibro_activo(libroCreateDTO.getLibroActivo());
			libro.setTitulo(libroCreateDTO.getTitulo());
			libro.setId_editorial(editorialRepositorio.getReferenceById(libroCreateDTO.getIdEditorial()));
			libro.setId_autor(autorRepositorio.getReferenceById(libroCreateDTO.getIdAutor()));
			
			libroRepositorio.save(libro);
		}

	// DELETE
	@Transactional
	public void excluirLibro(Integer id_libro) throws MiExcepcion {
		Libro libro = libroRepositorio.getReferenceById(id_libro);
		libro.setLibro_activo(false);
		
		libroRepositorio.save(libro);
	}
	
	// VALIDAR
	private void validar(LibroCreateDTO libroCreateDTO)
			throws MiExcepcion {
		final Integer isbn = libroCreateDTO.getIsbn();
		final String titulo = libroCreateDTO.getTitulo();
		final Integer ejemplares = libroCreateDTO.getEjemplares();
		final String id_autor = libroCreateDTO.getIdAutor();
		final String id_editorial = libroCreateDTO.getIdEditorial();
		
		if (isbn == null) {
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
