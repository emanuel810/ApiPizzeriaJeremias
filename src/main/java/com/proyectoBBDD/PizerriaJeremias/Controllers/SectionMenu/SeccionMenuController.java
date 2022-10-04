package com.proyectoBBDD.PizerriaJeremias.Controllers.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu.SeccionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seccionMenu")
public class SeccionMenuController {

    @Autowired
    private SeccionMenuService seccionMenuService;

    @GetMapping
    public List<SeccionMenuDto> listaSeccionMenu(){
        return seccionMenuService.listaSeccionMenu();
    }
    @GetMapping(value = "/{id}")
    public  SeccionMenuDto buscarSeccionMenu(@PathVariable Integer id){
        return seccionMenuService.buscarSeccionMenu(id);
    }
    @PostMapping()
    public SeccionMenuDto agregarSeccionMenu(@RequestBody SeccionMenuDto seccionMenuDto){
        return seccionMenuService.agregarSeccionMenu(seccionMenuDto);
    }

    @PutMapping()
    public  void editarSeccionMenu(@RequestBody SeccionMenuDto seccionMenuDto){
         seccionMenuService.editarSeccionMenu(seccionMenuDto);
    }

    @DeleteMapping
    public void borrarSeccionMenu(Integer id){
        seccionMenuService.borrarSeccionMenu(id);
    }
}
