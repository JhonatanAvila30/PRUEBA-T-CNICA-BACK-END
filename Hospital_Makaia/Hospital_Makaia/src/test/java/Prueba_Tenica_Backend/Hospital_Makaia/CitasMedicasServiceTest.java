package Prueba_Tenica_Backend.Hospital_Makaia;

import Prueba_Tenica_Backend.Hospital_Makaia.model.CitaMedica;
import Prueba_Tenica_Backend.Hospital_Makaia.repository.RepositoryCitaMedica;
import Prueba_Tenica_Backend.Hospital_Makaia.service.ServiceCitaMedica;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CitasMedicasServiceTest {

    private final RepositoryCitaMedica repositoryCitaMedica = Mockito.mock(RepositoryCitaMedica.class);
    private final ServiceCitaMedica serviceCitaMedica = new ServiceCitaMedica(repositoryCitaMedica);


    @Test
    public void CitaUsuarioParticularCitaExistente() {
        CitaMedica citaExistente = new CitaMedica();
        citaExistente.setIdentificacionUsuario("1234567890");
        citaExistente.setTipoUsuario("PARTICULAR");

        when(repositoryCitaMedica.findByidentificacionUsuario(anyString())).thenReturn(Optional.of(citaExistente));

        CitaMedica nuevaCita = new CitaMedica();
        nuevaCita.setIdentificacionUsuario("1234567890");
        nuevaCita.setTipoUsuario("PARTICULAR");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> serviceCitaMedica.agendarCita(nuevaCita));
        assertEquals("El usuario con identificacion 1234567890 ya tiene una cita agendada, por lo cual no podra realizar mas agendamientos.", exception.getMessage());
    }

    @Test
    public void calcularFechaCitaEPS(){
        LocalDate fechaEsperada = LocalDate.now().plusDays(14);
        LocalDate fechaCalculada = serviceCitaMedica.programarFechaCita("EPS");
        assertEquals(fechaEsperada, fechaCalculada);
    };




}
