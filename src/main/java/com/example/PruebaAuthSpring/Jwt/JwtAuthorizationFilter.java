/**
 * Clase para interceptar una solicitud HTTP, extraer el token de la cabecera
 * y validarlo.
 *
 * Obtenemos el token de la cabecera, extraemos el nombre de usuario y verificamos
 * que sea válido. Si todo va bien creamos el objeto de autenticación UsernamePasswordAuthenticationToken,
 * luego seteamos el SecurityContextHolder y permitimos que la solicitud
 * continue con el método filterChain.doFilter
 *
 */
package com.example.PruebaAuthSpring.Jwt;

import com.example.PruebaAuthSpring.Jwt.JWTService;
import com.example.PruebaAuthSpring.Usuario.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 *
 * @author char_
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String HEADER_TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(HEADER_TOKEN_PREFIX)) {
            String token = authorizationHeader.replace(HEADER_TOKEN_PREFIX, "");
            String username = jwtService.extractUsername(token);

            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
