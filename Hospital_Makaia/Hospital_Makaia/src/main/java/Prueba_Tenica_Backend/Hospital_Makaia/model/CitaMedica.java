package Prueba_Tenica_Backend.Hospital_Makaia.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Datos_Cita")
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id")
    private Long id;
    @Column (name = "Especialidad")
    private String especialidad;
    @Column (name = "Identificacion_usuario")
    private String identificacionUsuario;
    @Column (name = "Tipo_usuario")
    private String tipoUsuario;
    @Column (name = "Fecha_cita")
    private LocalDate fechaCita;

    public CitaMedica(){
    }

    public CitaMedica(Long id, String especialidad, String identificacionUsuario, String tipoUsuario, LocalDate fechaCita){
        this.id = id;
        this.especialidad = especialidad;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaCita = fechaCita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }
}

