/**
 * Repositorio del usuario
 */
package com.example.PruebaAuthSpring.Persistencia.Repositorio;

import com.example.PruebaAuthSpring.Persistencia.Entidades.Usuario;
import com.example.PruebaAuthSpring.Usuario.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author char_
 */
@Repository
public interface UserRepository extends JpaRepository<Usuario,Integer>{
   
    Usuario findByUsername(String nombre);
    Usuario findFirstByRole(Role role);
}
