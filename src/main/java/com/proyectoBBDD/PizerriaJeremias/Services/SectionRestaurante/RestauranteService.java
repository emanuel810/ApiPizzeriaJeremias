package com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante.RestauranteDto;

import java.util.List;

public interface RestauranteService {

    public List<RestauranteDto> listaRestaurante();

    public RestauranteDto buscarRestaurante(Integer id);

    public RestauranteDto agregarRestaurante(RestauranteDto restauranteDto);

    public void editarRestaurante(RestauranteDto restauranteDto);

    public void borrarRestaurante(Integer id);

}
