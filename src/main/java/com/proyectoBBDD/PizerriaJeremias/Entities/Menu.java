package com.proyectoBBDD.PizerriaJeremias.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu.SeccionMenu;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@Entity
@Data
@Table(name="menu")
public class Menu {

    @Id
    @Column(name="menuNumero")
    @Positive(message = "No se puede registro este tipo de numero")
    @NotNull(message = "Debe agregar un numero para el identificador del menu")
    private Integer numeroMenu;

    @Column(name="menuDescripcion")
    @NotBlank(message = "Debe agregar una descripcion al menu")
    private String menuDescripcion;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy="menu",cascade= CascadeType.MERGE)
    List<SeccionMenu> seccionMenus;



    public boolean validarDatos() {
        boolean retorno = true;
        if(menuDescripcion==null && menuDescripcion.isEmpty()) {
            retorno = false;

        }
        if(numeroMenu ==null && numeroMenu.describeConstable().isEmpty() || numeroMenu ==0)
        {
            retorno = false;
        }
        return retorno;
    }

    public Integer getNumeroMenu() {
        return numeroMenu;
    }

    public void setNumeroMenu(Integer numeroMenu) {
        this.numeroMenu = numeroMenu;
    }

    public String getMenuDescripcion() {
        return menuDescripcion;
    }

    public void setMenuDescripcion(String menuDescripcion) {
        this.menuDescripcion = menuDescripcion;
    }

    public List<SeccionMenu> getSeccionMenus() {
        return seccionMenus;
    }

    public void setSeccionMenus(List<SeccionMenu> seccionMenus) {
        this.seccionMenus = seccionMenus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "numeroMenu=" + numeroMenu +
                ", menuDescripcion='" + menuDescripcion + '\'' +
                ", seccionMenus=" + seccionMenus +
                '}';
    }
}