package com.pe.t2.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgramadorDTO {

    private Long idProgramador;

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El apellido no puede ser nulo")
    private String apellido;

    @NotNull(message = "El número de hijos no puede ser nulo")
    private Integer hijos;

    @DecimalMin(value = "0.00", message = "El sueldo mínimo permitido es 0.00")
    @DecimalMax(value = "10000.00", message = "El sueldo máximo permitido es 10000.00")
    private Double sueldo;
}
