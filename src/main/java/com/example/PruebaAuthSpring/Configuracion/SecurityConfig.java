/**
 * Clase de autenticación y autorización
 * Se definen que recursos deben de estar securizados y cuales no lo deben estar
 */
package com.example.PruebaAuthSpring.Configuracion;

import com.example.PruebaAuthSpring.Jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author char_
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthorizationFilter JwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/usuario/admin/**").hasRole("ADMIN")
                .requestMatchers("/usuario/altausuario").hasRole("ADMIN")
                .requestMatchers("/usuario/bajausuario/**").hasRole("ADMIN")
                .requestMatchers("/usuario/bajausuarioporid/**").hasRole("ADMIN")
                .requestMatchers("/usuario/listar").hasRole("ADMIN")
                .requestMatchers("/usuario/privado/**").hasRole("USER")
                .requestMatchers("/usuario/publico/**").permitAll()
                .requestMatchers("/usuario/login").permitAll()
                .requestMatchers("/incidencias/modificarestado/**").hasAnyRole("ADMIN", "MAINTENANCE")
                .anyRequest().authenticated()
                .and().addFilterBefore(JwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)// Añadimos el filtro
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Creamos una política sin estado
        //.httpBasic(withDefaults())
        //.formLogin();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

}
