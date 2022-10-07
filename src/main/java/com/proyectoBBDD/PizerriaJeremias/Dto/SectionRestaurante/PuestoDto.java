package com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PuestoDto {

    @NotNull(message = "Debe agregar un numero para el identificador del puesto")
    @Positive(message = "No se permite este tipo de numero")
    private Integer numeroPuesto;

    @NotBlank(message = "Debe agregar una descripcion del puesto")
    private String puestoDescripcion;
}
