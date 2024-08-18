/**
 * Controlador de incidencias
 */
package com.example.PruebaAuthSpring.Controladores;

import com.example.PruebaAuthSpring.Persistencia.Entidades.Incidencias;
import com.example.PruebaAuthSpring.Servicios.IncidenciasService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author char_
 */
@RestController
@RequestMapping("/incidencias")
@RequiredArgsConstructor
public class IncidenciasController {

    @Autowired
    IncidenciasService incidenciasService;

    //Crea una nueva incidencia
    @PostMapping("/alta")
    public String altaIncidencia(@RequestBody Incidencias incidencia) {
        incidenciasService.altaIncidencia(incidencia);
        return "Incidecia creada.";
    }

    //Modifica el estado de una incidencia
    @PutMapping("/modificarestado/{numIncidencia}")
    public String modificarEstado(@PathVariable Integer numIncidencia) {
        incidenciasService.modificarEstado(numIncidencia);
        return "estado modificado";
    }

    //Lista todas las incidencias
    @GetMapping("/listar")
    public List<Incidencias> listarIncidencias() {
        return incidenciasService.listarIncidencias();
    }

    //Obtiene el estado de la incidencia 'FINALIZADA' , 'PENDIENTE', etc.
    @GetMapping("/obtenerestado/{id}")
    public String getEstado(@PathVariable Integer id) {
        return incidenciasService.obtenerEstado(id);
    }

}
