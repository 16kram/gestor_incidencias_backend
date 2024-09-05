/**
 * Clase de servicio del usuario
 */
package com.example.PruebaAuthSpring.Servicios;

import com.example.PruebaAuthSpring.Persistencia.Repositorio.UserRepository;
import com.example.PruebaAuthSpring.Persistencia.Entidades.Usuario;
import com.example.PruebaAuthSpring.Usuario.Role;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author char_
 */
@Service
@NoArgsConstructor
public class UsuariosService {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    UserRepository userRepository;

    //Lista todos los usuarios
    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

    //Crea un nuevo usuario
    public ResponseEntity<?> altaUsuario(Usuario usuario) {
        //Comprueba si el usuario existe
        if (userRepository.findByUsername(usuario.getUsername()) != null) {
            return ResponseEntity.badRequest().build();//El usuario ya existe (Error 400)
        } else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            userRepository.save(usuario);
            return ResponseEntity.ok("Se ha creado un nuevo usuario");
            
        }
    }

    //Elimina un usuario por nombre
    public void bajaUsuario(String usuario) {
        userRepository.deleteById(userRepository.findByUsername(usuario).getId());
    }

    //Elimina un usuario por id
    public void bajaUsuarioPorId(Integer id) {
        userRepository.deleteById(id);
    }

    //Obtiene el solicitante para crear una incidencia
    public String solicitanteIncidencia(String usuario) {
        String nombreApellidos = (userRepository.findByUsername(usuario).getFirstname()
                + " " + userRepository.findByUsername(usuario).getLastname())
                .toUpperCase();
        System.out.println("nombre=" + nombreApellidos);
        return nombreApellidos;
    }

    //Obtiene el rol de un usuario
    public String rolUsuario(String usuario) {
        var rolUsuario = userRepository.findByUsername(usuario).getRole();
        System.out.println("nombre=" + rolUsuario);
        return rolUsuario.toString();
    }

    //Crea un usuario administrador si no existe en la base de datos
    public String creaAdministrador() {
        Usuario usr = userRepository.findFirstByRole(Role.ROLE_ADMIN);
        if (usr == null) {
            System.out.println("no exixte el usuario");
            Usuario usuario = new Usuario();
            usuario.setUsername("admin");
            usuario.setPassword(passwordEncoder.encode("1234"));
            usuario.setRole(Role.ROLE_ADMIN);
            usuario.setFirstname("Usuario");
            usuario.setLastname("administrador");
            usuario.setCountry("Barcelona");
            userRepository.save(usuario);
            return "Usuario admin creado.";
        } else {
            return "El usuario administrador ya existe.";
        }
    }
    
}
