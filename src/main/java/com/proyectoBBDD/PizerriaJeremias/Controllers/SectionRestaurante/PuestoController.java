package com.proyectoBBDD.PizerriaJeremias.Controllers.SectionRestaurante;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante.PuestoDto;
import com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante.PuestoService;
import io.swagger.models.auth.In;
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
@RequestMapping("/puesto")
public class PuestoController {

    @Autowired
    private PuestoService puestoService;

    @GetMapping
    public List<PuestoDto> listaPuesto(){
        return puestoService.listaPuesto();
    }
    @GetMapping(value = "/{id}")
    public PuestoDto buscarPuesto(@PathVariable Integer id){
        return puestoService.buscarPuesto(id);
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PuestoDto agregarPuesto(@Valid @RequestBody PuestoDto puestoDto){
        return puestoService.agregarPuesto(puestoDto);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void editarPuesto(@Valid @RequestBody PuestoDto puestoDto){
        puestoService.editarPuesto(puestoDto);
    }

    @DeleteMapping(value = "/{id}")
    public void borrarPuesto(@PathVariable Integer id){
        puestoService.borrarPuesto(id);
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
