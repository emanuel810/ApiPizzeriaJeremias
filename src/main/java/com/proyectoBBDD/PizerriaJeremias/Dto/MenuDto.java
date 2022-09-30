package com.proyectoBBDD.PizerriaJeremias.Dto;

import lombok.Data;

@Data
public class MenuDto {

    private Integer menuNumero;

    private String menuDescripcion;

    private int codigoError;

    private String mensajeError;
}
