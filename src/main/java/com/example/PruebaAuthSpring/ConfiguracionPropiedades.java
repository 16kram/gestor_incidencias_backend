/**
 * Configuración de los parámetros globales de la aplicación
 */
package com.example.PruebaAuthSpring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author char_
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class ConfiguracionPropiedades {

    private String clave;
}
