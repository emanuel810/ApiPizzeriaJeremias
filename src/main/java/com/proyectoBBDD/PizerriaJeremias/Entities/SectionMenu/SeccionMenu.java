package com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectoBBDD.PizerriaJeremias.Entities.Menu;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "SeccionMenu")
public class SeccionMenu {

    @Id
    private Integer seccionMenuNumero;

    @Column(name = "seccinMenuDescripcion")
    private String seccinMenuDescripcion;

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="menuNumero")
    private Menu menu;

    public boolean validarDatos(){
        boolean retorno = true;
        if(seccionMenuNumero==null && seccionMenuNumero.describeConstable().isEmpty() || seccionMenuNumero==0){
            retorno =false;
        }
        if(seccinMenuDescripcion==null && seccinMenuDescripcion.isEmpty()){
            retorno = false;
        }
        return  retorno;
    }
}
