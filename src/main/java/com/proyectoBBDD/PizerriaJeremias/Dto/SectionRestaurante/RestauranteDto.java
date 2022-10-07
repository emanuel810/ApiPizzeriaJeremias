package com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante;


import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RestauranteDto {

    @NotNull(message = "Debe agregar un numero para el identificador del restaurante")
    @Positive(message = "No se permite este tipo de numero")
    private Integer restauranteNumero;

    @NotBlank(message = "Debe agregar una descripcion del restaurante")
    private String nombreRestaurante;

}
