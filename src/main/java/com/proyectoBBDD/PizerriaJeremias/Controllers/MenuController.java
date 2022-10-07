package com.proyectoBBDD.PizerriaJeremias.Controllers;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;
import com.proyectoBBDD.PizerriaJeremias.Services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseStatus(code = HttpStatus.CREATED)
    public MenuDto agregarMenu(@Valid @RequestBody MenuDto menuDto) {
        return menuService.agregarMenu(menuDto);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void editarMenu(@Valid @RequestBody MenuDto menuDto){
        menuService.editarMenu(menuDto);
    }

    @DeleteMapping("/{id}")
    public void borrarMenu(@PathVariable Integer id){
        menuService.borrarMenu(id); ;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldname =((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldname,errorMessage);
        });
        return errors ;
    }




}
