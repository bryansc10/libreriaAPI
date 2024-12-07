package com.soca.libreriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soca.libreriaapi.entidades.Libro;
import com.soca.libreriaapi.modelos.LibroListarActivosDTO;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
	@Query("SELECT new com.soca.libreriaapi.modelos.LibroListarActivosDTO(l.titulo, l.ejemplares) "
			+ "FROM Libro l WHERE l.libro_activo = true")
	List<LibroListarActivosDTO> encontrarActivos();
}
