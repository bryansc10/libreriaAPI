package com.soca.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soca.libreriaapi.entidades.Editorial;
import com.soca.libreriaapi.excepciones.MiExcepcion;
import com.soca.libreriaapi.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {
	@Autowired
	private EditorialRepositorio editorialRepositorio;

	// CREATE
	@Transactional
	public void crearEditorial(String nombreEditorial, Boolean actividad) throws MiExcepcion {
		validarNombre(nombreEditorial);
		Editorial editorial = new Editorial();
		editorial.setNombre_editorial(nombreEditorial);
		editorial.setEditorial_activa(actividad);
		editorialRepositorio.save(editorial);
	}

	// DELETE
	@Transactional
	public void excluirEditorial(String id) throws MiExcepcion {
		validarID(id);
		obtenerEditorial(id).setEditorial_activa(false);
	}

	// UPDATE
	@Transactional
	public void incluirEditorial(String id) throws MiExcepcion {
		validarID(id);
		obtenerEditorial(id).setEditorial_activa(true);
	}

	@Transactional
	public void cambiarNombre(String nombreEditorial, String id) throws MiExcepcion {
		validarID(id);
		validarNombre(nombreEditorial);
		obtenerEditorial(id).setNombre_editorial(nombreEditorial);
	}

	// READ
	@Transactional(readOnly = true)
	public Editorial obtenerEditorial(String id_editorial) throws MiExcepcion {
		validarID(id_editorial);
		return editorialRepositorio.findById(id_editorial).get();
	}

	@Transactional(readOnly = true)
	public List<Editorial> listarEditoriales() throws MiExcepcion {
		return editorialRepositorio.findAll();
	}

	// VALIDAR
	private void validarNombre(String nombre) throws MiExcepcion {
		if (nombre.isEmpty() || nombre == null)
			throw new MiExcepcion("El nombre no puede ser nulo o vacío.");
	}

	private void validarID(String id) throws MiExcepcion {
		Optional<Editorial> editorial = editorialRepositorio.findById(id);
		if (!editorial.isPresent())
			throw new MiExcepcion("No se encontro una editorial con ese id.");
	}
}
