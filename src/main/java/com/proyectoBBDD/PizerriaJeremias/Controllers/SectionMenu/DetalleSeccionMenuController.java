package com.proyectoBBDD.PizerriaJeremias.Controllers.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.DetalleSeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu.DetalleSeccionMenuService;
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
@RequestMapping("detalleSeccionMenu")
public class DetalleSeccionMenuController {


    @Autowired
    private DetalleSeccionMenuService detalleSeccionMenuService;

    @GetMapping
    public List<DetalleSeccionMenuDto> listaSeccionMenu(){
        return detalleSeccionMenuService.listaDetalleSeccionMenu();
    }

    @GetMapping(value = "/{id}")
    public  DetalleSeccionMenuDto buscarSeccionMenu(@PathVariable Integer id){
        return detalleSeccionMenuService.buscarDetalleSeccionMenu(id);
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public DetalleSeccionMenuDto agregarSeccionMenu(@Valid @RequestBody DetalleSeccionMenuDto detalleSeccionMenuDto){
        return detalleSeccionMenuService.agregarDetalleSeccionMenu(detalleSeccionMenuDto);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public  void editarSeccionMenu(@Valid @RequestBody DetalleSeccionMenuDto detalleSeccionMenuDto){
        detalleSeccionMenuService.editarDetalleSeccionMenu(detalleSeccionMenuDto);
    }

    @DeleteMapping
    public void borrarSeccionMenu(Integer id){
        detalleSeccionMenuService.borrarDetalleSeccionMenu(id);
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
