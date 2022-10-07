package com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class DetalleSeccionMenuDto {


    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el detalle de la seccion del menu")
    private Integer detalleSeccionNumero;


    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el precio")
    private  Integer precio;

    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para la seccion del menu")
    private Integer seccionMenuNumero;

    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el menu")
    private Integer menuNumero;

    private int codigoError;

    private String mensajeError;
}
