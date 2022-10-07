package com.proyectoBBDD.PizerriaJeremias.Entities.SectionRestaurante;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name="restaurante")
public class Restaurante {

    @Id
    @NotNull(message = "Debe agregar un numero para el identificador del restaurante")
    @Positive(message = "No se permite este tipo de numero")
    @Column(name = "restauranteNumero")
    private Integer numeroRestaurante;

    @NotBlank(message = "Debe agregar una descripcion del restaurante")
    @Column(name = "nombreRestaurante")
    private String nombreRestaurante;
}
