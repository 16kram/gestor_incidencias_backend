/**
 * Clase de servicio de incidencias
 */
package com.example.PruebaAuthSpring.Servicios;

import com.example.PruebaAuthSpring.Incidencias.EstadoIncidencia;
import com.example.PruebaAuthSpring.Persistencia.Entidades.Incidencias;
import com.example.PruebaAuthSpring.Persistencia.Repositorio.IncidenciasRepository;
import com.example.PruebaAuthSpring.Persistencia.Repositorio.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author char_
 */
@Service
@NoArgsConstructor
public class IncidenciasService {

    @Autowired
    IncidenciasRepository incidenciasRepository;

    @Autowired
    UserRepository userRepository;

    //Crea una nueva incidencia
    public void altaIncidencia(Incidencias incidencias) {
        //Añadimos la fecha actual
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        incidencias.setFechaInicio(formato.format(fechaActual));

        //Añadimos el estado de pendiente
        incidencias.setEstado(EstadoIncidencia.PENDIENTE);

        incidenciasRepository.save(incidencias);
    }

    //Modifica el estado de las incidencias
    public void modificarEstado(Integer numIncidencia) {
        Incidencias incidencias = incidenciasRepository.getReferenceById(numIncidencia);
        String estado = incidencias.getEstado().toString();

        switch (estado) {
            case "PENDIENTE":
                incidencias.setEstado(EstadoIncidencia.EN_CURSO);
                break;
            case "EN_CURSO":
                incidencias.setEstado(EstadoIncidencia.FINALIZADA);
                //Añadimos la fecha final
                Date fechaActual = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                incidencias.setFechaFin(formato.format(fechaActual));
                break;
            case "FINALIZADA":
                break;
        }
        incidenciasRepository.save(incidencias);
    }

    //Lista todas las incidencias
    public List<Incidencias> listarIncidencias(String idUsuario) {
        String rolUsuario = userRepository.findByUsername(idUsuario).getRole().toString();
        if ("ROLE_ADMIN".equals(rolUsuario) || "ROLE_MAINTENANCE".equals(rolUsuario)) {
            return incidenciasRepository.findAll();
        } else {
            return incidenciasRepository.findByIdUsuario(idUsuario);
        }
    }

    //Obtiene el estado de la incidencia 'FINALIZADA' , 'PENDIENTE', etc.
    public String obtenerEstado(Integer numIncidencia) {
        Incidencias incidencias = incidenciasRepository.getReferenceById(numIncidencia);
        String estado = incidencias.getEstado().toString();
        return estado;
    }
}
