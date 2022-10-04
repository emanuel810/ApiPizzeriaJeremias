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
    public List<MenuDto> listaMenu(){
        return menuService.listarMenu();
    }

    @GetMapping(value= "/{id}")
    public MenuDto buscarMenu(@PathVariable Integer id){
        return  menuService.buscarMenu(id);
    }

    @PostMapping()
    public MenuDto agregarMenu(@RequestBody MenuDto menuDto) {
        return menuService.agregarMenu(menuDto);
    }


    @PutMapping()
    public void editarMenu(@RequestBody MenuDto menuDto){
        menuService.editarMenu(menuDto);
    }

    @DeleteMapping("/{id}")
    public void borrarMenu(@PathVariable Integer id){
        menuService.borrarMenu(id); ;
    }




}
