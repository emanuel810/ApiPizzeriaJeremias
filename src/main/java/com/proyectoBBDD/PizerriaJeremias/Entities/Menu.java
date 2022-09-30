package com.proyectoBBDD.PizerriaJeremias.Entities;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="menu")
public class Menu {

    @Id
    private Integer menuNumero;

    @Column(name="menuDescripcion")
    private String menuDescripcion;

    public boolean validarDatos() {
        boolean retorno = true;
        if(menuDescripcion==null && menuDescripcion.isEmpty()) {
            retorno = false;

        }
        return retorno;
    }

}