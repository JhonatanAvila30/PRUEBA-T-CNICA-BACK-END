package Prueba_Tenica_Backend.Hospital_Makaia.service;

import Prueba_Tenica_Backend.Hospital_Makaia.model.CitaMedica;
import Prueba_Tenica_Backend.Hospital_Makaia.repository.RepositoryCitaMedica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ServiceCitaMedica {

    private RepositoryCitaMedica repositoryCitaMedica;

    @Autowired
    public ServiceCitaMedica(RepositoryCitaMedica repositoryCitaMedica) {
        this.repositoryCitaMedica = repositoryCitaMedica;
    }

    public CitaMedica agendarCita (CitaMedica cita){
        if (!cita.getTipoUsuario().matches("EPS | POLIZA | PARTICULAR")) {
            throw new IllegalArgumentException("Tipo de usuario no permitido en el hospital");
        }
            Optional<CitaMedica> existingCita = repositoryCitaMedica.findByidentificacionUsuario(cita.getIdentificacionUsuario());

        if (existingCita.isPresent() && cita.getTipoUsuario().equals("PARTICULAR")) {
            throw new IllegalArgumentException("El usuario con identificacion " + cita.getIdentificacionUsuario() + " ya tiene una cita agendada, por lo cual no podra realizar mas agendamientos.");
        }

        cita.setFechaCita(programarFechaCita(cita.getTipoUsuario()));
        return repositoryCitaMedica.save(cita);
    }

    public LocalDate programarFechaCita(String tipoUsuario){
        LocalDate fechaActual = LocalDate.now();
        int dias = 0;

        switch (tipoUsuario){
            case "EPS":dias = 10;
            break;

            case "POLIZA":dias = 8;
            break;

            case "PARTICULAR":dias = 7;
            break;
        }

        LocalDate fechaCita = fechaActual;
        while (dias > 0){
            fechaCita = fechaCita.plusDays(1);
            if (tipoUsuario.equals("POLIZA") || (fechaCita.getDayOfWeek() != DayOfWeek.SATURDAY && fechaCita.getDayOfWeek() != DayOfWeek.SUNDAY)){
                dias --;
            }
        }
        return fechaCita;
    }

    public Optional<CitaMedica> obtenerCita(Long id){
        return repositoryCitaMedica.findById(id);
    }


}

