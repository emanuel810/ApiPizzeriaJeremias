package com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SeccionMenuDto {

    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para la seccion del menu")
    private Integer seccionMenuNumero;

    @NotBlank(message = "Debe agregar un la descripcion para la seccion del menu")
    private String seccionMenuDescripcion;

    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el menu")
    private int menuNumero;



    private int codigoError;

    private String mensajeError;


}
