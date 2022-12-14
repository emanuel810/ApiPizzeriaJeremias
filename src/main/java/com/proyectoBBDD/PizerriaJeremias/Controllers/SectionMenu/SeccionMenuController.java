package com.proyectoBBDD.PizerriaJeremias.Controllers.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu.SeccionMenuService;
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public SeccionMenuDto agregarSeccionMenu(@Valid @RequestBody SeccionMenuDto seccionMenuDto){
        return seccionMenuService.agregarSeccionMenu(seccionMenuDto);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public  void editarSeccionMenu(@Valid @RequestBody SeccionMenuDto seccionMenuDto){
         seccionMenuService.editarSeccionMenu(seccionMenuDto);
    }

    @DeleteMapping
    public void borrarSeccionMenu(Integer id){
        seccionMenuService.borrarSeccionMenu(id);
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
