package com.proyectoBBDD.PizerriaJeremias.Entities.SectionRestaurante;

import lombok.Data;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "puesto")
public class Puesto {

    @Id
    @Column(name = "puestoNumero")
    @NotNull(message = "Debe agregar un numero para el identificador del puesto")
    @Positive(message = "No se permite este tipo de numero")
    private Integer numeroPuesto;

    @Column(name = "puestoDescripcion")
    @NotBlank(message = "Debe agregar una descripcion del puesto")
    private String puestoDescripcion;




}
