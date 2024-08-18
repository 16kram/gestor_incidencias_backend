/**
 * Controlador de Usuario
 */
package com.example.PruebaAuthSpring.Controladores;

import com.example.PruebaAuthSpring.Autenticacion.AuthenticationRequest;
import com.example.PruebaAuthSpring.Autenticacion.AuthenticationResponse;
import com.example.PruebaAuthSpring.Jwt.JWTService;
import com.example.PruebaAuthSpring.Usuario.MyUserDetailService;
import com.example.PruebaAuthSpring.Persistencia.Entidades.Usuario;
import com.example.PruebaAuthSpring.Servicios.UsuariosService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author char_
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuariosController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService myUserDetailService;
    private final JWTService jwtService;

    @Autowired
    UsuariosService usuariosService;

    //Endpoint PUBLICO para pruebas
    @GetMapping("/publico")
    public ResponseEntity<?> publico() {
        return ResponseEntity.ok("Hola desde público");
    }

    //Endpoint PRIVADO para pruebas
    @GetMapping("/privado")
    public String privado() {
        return "Hola desde privado para rol USUARIOS";
    }

    //Endpoint ADMIN de pruebas
    @GetMapping("/admin")
    public String admin() {
        return "Hola desde privado para rol ADMIN";
    }

    //Lista los usuarios
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuariosService.listarUsuarios());
    }

    /**
     * Controlador de login. Se crea un objeto
     * UsernamePasswordAuthenticationToken con la petición POST que contiene el
     * usuario y la contraseña, se llama a
     * authenticationManager.authenticate(authentication) para su validación, si
     * falla se crea una excepción, en caso contrario se crea el token y se
     * devuelve como respuesta al usuario.
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("El usuario o la contraseña no coinciden", e);
        }
        UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.createToken(userDetails);
        return new AuthenticationResponse(token);
    }

    //Crea un usuario
    @PostMapping("/alta")
    public ResponseEntity<?> altaUsuario(@RequestBody Usuario usuario) {
        return usuariosService.altaUsuario(usuario);
    }

    //Elimina un usuario por nombre
    @DeleteMapping("/baja/{usuario}")
    public String bajaUsuario(@PathVariable String usuario) {
        usuariosService.bajaUsuario(usuario);
        return "usuario eliminado.";
    }

    //Elimina un usuario por id
    @DeleteMapping("/bajaporid/{id}")
    public String bajaUsuarioPorId(@PathVariable Integer id) {
        usuariosService.bajaUsuarioPorId(id);
        return "usuario eliminado.";
    }

    //Obtiene el solicitante para crear una incidencia
    @GetMapping("/nombreapellidos/{usuario}")
    public String SolicitanteIncidencia(@PathVariable String usuario) {
        return usuariosService.solicitanteIncidencia(usuario);
    }

    //Obtiene el rol de un usuario
    @GetMapping("rolusuario/{usuario}")
    public String rolUsuario(@PathVariable String usuario) {
        return usuariosService.rolUsuario(usuario);
    }

}
