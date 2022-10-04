package com.proyectoBBDD.PizerriaJeremias.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu.SeccionMenu;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="menu")
public class Menu {

    @Id
    private Integer menuNumero;

    @Column(name="menuDescripcion")
    private String menuDescripcion;

    @JsonIgnore
    @OneToMany(mappedBy="menu",cascade= CascadeType.MERGE)
    List<SeccionMenu> seccionMenus;

    public boolean validarDatos() {
        boolean retorno = true;
        if(menuDescripcion==null && menuDescripcion.isEmpty()) {
            retorno = false;

        }
        if(menuNumero==null && menuNumero.describeConstable().isEmpty() || menuNumero==0)
        {
            retorno = false;
        }
        return retorno;
    }

}