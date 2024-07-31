package Prueba_Tenica_Backend.Hospital_Makaia.controller;

import Prueba_Tenica_Backend.Hospital_Makaia.model.CitaMedica;
import Prueba_Tenica_Backend.Hospital_Makaia.service.ServiceCitaMedica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/citaMedica")
public class ControllerCitaMedica {

    @Autowired
    private ServiceCitaMedica serviceCitaMedica;


    @PostMapping
    public ResponseEntity<?> agendarCita(@RequestBody CitaMedica cita){
        try {CitaMedica nuevaCita = serviceCitaMedica.agendarCita(cita);
            return ResponseEntity.ok(nuevaCita);
        }
        catch (IllegalArgumentException e){
        return ResponseEntity.badRequest().body("{\"mensaje\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable Long id){
        Optional<CitaMedica> cita = serviceCitaMedica.obtenerCita(id);
         return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
