/**
 * Después de cargarse la configuración de WebSecurity, cuando el usuario
 * introduce sus credenciales (usuario y contraseña) Basic Auth
 * ejemplo: {"username":"epor","password":"1234"} con POST,
 * desde /login
 *
 * El método loadUserByUserName recibe el nombre de usuario.
 *
 * Se crea un objeto MyUserDetails con el nombre de usuario y todos los demás
 * valores que tengamos en la base de datos.
 */
package com.example.PruebaAuthSpring.Usuario;

import com.example.PruebaAuthSpring.Usuario.MyUserDetails;
import com.example.PruebaAuthSpring.Persistencia.Entidades.Usuario;
import com.example.PruebaAuthSpring.Persistencia.Repositorio.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author char_
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username);
        return new MyUserDetails(user);
    }

}
