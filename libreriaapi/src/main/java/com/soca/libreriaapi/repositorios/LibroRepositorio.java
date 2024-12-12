package com.soca.libreriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soca.libreriaapi.entidades.Libro;
import com.soca.libreriaapi.modelos.LibroListarActivosDTO;
import com.soca.libreriaapi.modelos.LibrosPorEntidadDTO;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
	@Query("SELECT new com.soca.libreriaapi.modelos.LibroListarActivosDTO(l.titulo, l.ejemplares) "
			+ "FROM Libro l WHERE l.libro_activo = true")
	List<LibroListarActivosDTO> encontrarActivos();

	@Query("SELECT new com.soca.libreriaapi.modelos.LibrosPorEntidadDTO(l.titulo, a.nombre_autor, e.nombre_editorial, l.libro_activo, l.ejemplares) "
	        + "FROM Libro l "
	        + "JOIN l.id_autor a "
	        + "JOIN l.id_editorial e "
	        + "WHERE e.id_editorial = :id")
	List<LibrosPorEntidadDTO> listarLibrosPorEditorial(@Param("id") String id);
	
	@Query("SELECT new com.soca.libreriaapi.modelos.LibrosPorEntidadDTO(l.titulo, a.nombre_autor, e.nombre_editorial, l.libro_activo, l.ejemplares) "
	        + "FROM Libro l "
	        + "JOIN l.id_autor a "
	        + "JOIN l.id_editorial e "
	        + "WHERE a.id_autor = :id")
	List<LibrosPorEntidadDTO> listarLibrosPorAutor(@Param("id") String id);
}
