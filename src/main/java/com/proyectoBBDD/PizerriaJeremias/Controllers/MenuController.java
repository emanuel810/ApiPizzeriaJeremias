package com.proyectoBBDD.PizerriaJeremias.Controllers;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;
import com.proyectoBBDD.PizerriaJeremias.Services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuDto> listaEmpresa(){
        return menuService.listarMenu();
    }

    @GetMapping(value= "/{id}")
    public MenuDto obtenerEmpresa(@PathVariable Integer id){
        return  menuService.buscarMenu(id);
    }

    @PostMapping()
    public MenuDto crearEmpresa(@RequestBody MenuDto menuDto) {
        return menuService.crearMenu(menuDto);
    }


    @PutMapping()
    public void modificarEmpresa(@RequestBody MenuDto menuDto){
        menuService.editarMenu(menuDto);
    }

    @DeleteMapping("/{id}")
    public void borrarEmpresa(@PathVariable Integer id){
        menuService.borrarMenu(id); ;
    }




}
