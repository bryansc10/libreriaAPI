package com.soca.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soca.libreriaapi.entidades.Autor;
import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.modelos.AutorModificarDTO;
import com.soca.libreriaapi.repositorios.AutorRepositorio;

@Service
public class AutorServicio {
	@Autowired
	private AutorRepositorio autorRepositorio;
	@Autowired
	private ModelMapper modelMapper;

	// CREATE
	@Transactional
	public void crearAutor(String nombreAutor, Boolean actividad) throws MiExcepcion {
		validarNombre(nombreAutor);
		Autor autor = new Autor();
		autor.setNombre_autor(nombreAutor);
		autor.setAutor_activo(actividad);
		autorRepositorio.save(autor);
	}

	// DELETE
	@Transactional
	public void excluirAutor(String id) throws MiExcepcion {
		validarID(id);
		obtenerAutor(id).setAutor_activo(false);
	}

	// UPDATE
	@Transactional
	public void incluirAutor(String id) throws MiExcepcion {
		validarID(id);
		obtenerAutor(id).setAutor_activo(true);
	}

	@Transactional
	public void cambiarNombre(AutorModificarDTO modificarDTO) throws MiExcepcion {
		validarID(modificarDTO.getId_autor());
		validarNombre(modificarDTO.getNombre_autor());
		Autor autor = autorRepositorio.getReferenceById(modificarDTO.getId_autor());
		modelMapper.map(modificarDTO, autor);
		
		autorRepositorio.save(autor);
	}

	// READ
	@Transactional(readOnly = true)
	public Autor obtenerAutor(String id_autor) throws MiExcepcion {
		validarID(id_autor);
		return autorRepositorio.getReferenceById(id_autor);
	}

	@Transactional(readOnly = true)
	public List<Autor> listarAutores() throws MiExcepcion {
		return autorRepositorio.findAll();
	}

	// VALIDAR
	private void validarNombre(String nombre) throws MiExcepcion {
		if (nombre.isEmpty() || nombre == null)
			throw new MiExcepcion("El nombre no puede ser nulo o vacío.");
	}

	private void validarID(String id) throws MiExcepcion {
		Optional<Autor> autor = autorRepositorio.findById(id);
		if (!autor.isPresent())
			throw new MiExcepcion("No se encontro un(a) autor(a) con ese id.");
	}
}
