/**
 * Repositorio de incidencias
 */
package com.example.PruebaAuthSpring.Persistencia.Repositorio;

import com.example.PruebaAuthSpring.Persistencia.Entidades.Incidencias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author char_
 */
public interface IncidenciasRepository extends JpaRepository<Incidencias, Integer> {

    List<Incidencias> findByIdUsuario(String nombre);
}
