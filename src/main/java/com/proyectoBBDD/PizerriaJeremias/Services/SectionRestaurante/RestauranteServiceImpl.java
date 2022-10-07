package com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante.RestauranteDto;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionRestaurante.Restaurante;
import com.proyectoBBDD.PizerriaJeremias.Repository.SectionRestaurante.RestauranteRepository;
import com.proyectoBBDD.PizerriaJeremias.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RestauranteServiceImpl  implements  RestauranteService{

    private final ModelMapper modelMapper;

    private final RestauranteRepository restauranteRepository;
    public RestauranteServiceImpl(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
        this.modelMapper = new ModelMapper();
    }


    public RestauranteDto mappedRestauranteDto(Restaurante restaurante){
         return modelMapper.map(restaurante,RestauranteDto.class);
    }

    public Restaurante mappedRestaurante(RestauranteDto restauranteDto){
        return modelMapper.map(restauranteDto,Restaurante.class);
    }


    @Override
    public List<RestauranteDto> listaRestaurante() {
        List<Restaurante>  restauranteList = (List<Restaurante>) restauranteRepository.findAll();
        List<RestauranteDto> restauranteDtoList = new ArrayList<>();
        for (Restaurante restaurante : restauranteList){
            restauranteDtoList.add(mappedRestauranteDto(restaurante));
        }
        return restauranteDtoList;
    }

    @Override
    public RestauranteDto buscarRestaurante(Integer id) {

        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        if (restauranteOptional.isPresent()){
            Restaurante restauranteTemp = restauranteOptional.get();
            RestauranteDto restauranteDto = mappedRestauranteDto(restauranteTemp);
            return restauranteDto;
        }else {
            throw new NotFoundException("No se encontro el restaurante");
        }
    }

    @Override
    public RestauranteDto agregarRestaurante(RestauranteDto restauranteDto) {

        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restauranteDto.getRestauranteNumero());

        Restaurante restaurante = mappedRestaurante(restauranteDto);

        if(!restauranteOptional.isPresent()){
            Restaurante restauranteTemp = restauranteRepository.save(restaurante);
            restauranteDto.setRestauranteNumero(restauranteTemp.getNumeroRestaurante());
        }else{
            throw new NotFoundException("No se permite identificadores repetidos");
        }


        return restauranteDto;
    }

    @Override
    public void editarRestaurante(RestauranteDto restauranteDto) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restauranteDto.getRestauranteNumero());
        if(restauranteOptional.isPresent()){
            Restaurante restauranteTemp = restauranteOptional.get();
            if(!restauranteTemp.getNombreRestaurante().equals(restauranteDto.getNombreRestaurante())){
                restauranteTemp.setNombreRestaurante(restauranteDto.getNombreRestaurante());
            }
            restauranteRepository.save(restauranteTemp);
        }else{
            throw new NotFoundException("No se encontro el restaurante");
        }
    }

    @Override
    public void borrarRestaurante(Integer id) {

        if(restauranteRepository.existsById(id)){
            restauranteRepository.deleteById(id);
        }else {
            throw new NotFoundException("No se encontro el restaurante");
        }

    }
}
