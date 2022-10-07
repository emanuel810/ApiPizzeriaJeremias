package com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyectoBBDD.PizerriaJeremias.Entities.Menu;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;




@Data
@Entity
@Table(name = "seccionMenu")
public class SeccionMenu {
    @Id
    @Column(name="seccionMenuNumero")
    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para la seccion del menu")
    private Integer numeroSeccionMenu;
    @Column(name = "seccinMenuDescripcion")
    @NotBlank(message = "Debe agregar un la descripcion para la seccion del menu")
    private String seccionMenuDescripcion;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="menuNumero")
    private Menu menu;


    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy="seccionMenu",cascade= CascadeType.MERGE)
    List<DetalleSeccionMenu> detalleSeccionMenus;


    public boolean validarDatos(){
        boolean retorno = true;
        if(numeroSeccionMenu ==null && numeroSeccionMenu.describeConstable().isEmpty() || numeroSeccionMenu ==0){
            retorno =false;
        }
        if(seccionMenuDescripcion==null && seccionMenuDescripcion.isEmpty()){
            retorno = false;
        }
        return  retorno;
    }

}
