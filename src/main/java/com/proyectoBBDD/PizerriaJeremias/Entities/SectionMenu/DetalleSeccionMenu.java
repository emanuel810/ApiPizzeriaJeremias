package com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "detalleSeccionMenu")
public class DetalleSeccionMenu {

    @Id
    @Column(name="detalleSeccionNumero")
    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el detalle de la seccion del menu")
    private Integer numeroDetalleSeccion;

    @Column(name = "Precio")
    @NotNull(message = "Debe agregar un la descripcion para la seccion del menu")
    private Integer precio;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="seccionMenuNumero")
    private SeccionMenu seccionMenu;




    public boolean validarDatos(){
        boolean retorno = true;
        if(numeroDetalleSeccion ==null || numeroDetalleSeccion.describeConstable().isEmpty() || numeroDetalleSeccion ==0){
            retorno =false;
        }
        if(precio==null || precio.describeConstable().isEmpty() || precio==0){
            retorno =false;
        }

        return  retorno;
    }
}
