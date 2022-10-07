package com.proyectoBBDD.PizerriaJeremias.Controllers.SectionRestaurante;


import com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante.RestauranteDto;
import com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante.RestauranteService;
import com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante.RestauranteServiceImpl;
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
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<RestauranteDto> listaRestaurante(){
        return restauranteService.listaRestaurante();
    }

    @GetMapping(value = "/{id}")
    public RestauranteDto buscarRestaurante(@PathVariable Integer id){
        return  restauranteService.buscarRestaurante(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RestauranteDto agregarRestaurante(@Valid @RequestBody RestauranteDto restauranteDto){
        return restauranteService.agregarRestaurante(restauranteDto);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void editarRestaurante(@Valid @RequestBody RestauranteDto restauranteDto){
         restauranteService.editarRestaurante(restauranteDto);
    }

    @DeleteMapping(value = "/{id}")
    public void borrarRestaurante(@PathVariable Integer id){
        restauranteService.borrarRestaurante(id);
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
