package com.proyectoBBDD.PizerriaJeremias.Dto;

import lombok.Data;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class MenuDto {

    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el menu")
    private Integer menuNumero;

    @NotBlank(message = "Debe agregar un la descripcion del menu")
    private String menuDescripcion;

    private int codigoError;

    private String mensajeError;
}
