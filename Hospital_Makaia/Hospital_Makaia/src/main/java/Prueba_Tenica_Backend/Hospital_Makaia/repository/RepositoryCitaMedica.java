package Prueba_Tenica_Backend.Hospital_Makaia.repository;

import Prueba_Tenica_Backend.Hospital_Makaia.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCitaMedica extends JpaRepository <CitaMedica, Long> {

    Optional<CitaMedica> findByidentificacionUsuario(String identificaionUsuario);
}
