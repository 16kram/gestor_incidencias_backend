/**
 * Maneja los problemas con CORS si la solicitud proviene de un entorno local
 * o el navegador no puede determinar el origen
 *
 * He añadido esta clase porque desde la página web no me dejaba consumir
 * los datos desde javascript con fetch, debido a un error de CORS por tener
 * en el encabezado 'origin' un valor null, que indica que la solicitud
 * proviene de un entorno local o que el navegador no puede determinar el origen
 */
package com.example.PruebaAuthSpring.Configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author char_
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080/", "null") // Permite solicitudes de 'null'
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
