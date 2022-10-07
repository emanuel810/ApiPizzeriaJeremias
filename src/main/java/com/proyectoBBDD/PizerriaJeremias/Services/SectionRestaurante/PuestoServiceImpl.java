package com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante.PuestoDto;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionRestaurante.Puesto;
import com.proyectoBBDD.PizerriaJeremias.Repository.SectionRestaurante.PuestoRepository;
import com.proyectoBBDD.PizerriaJeremias.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PuestoServiceImpl implements  PuestoService{

    private final ModelMapper modelMapper;

    private final PuestoRepository puestoRepository;

    public PuestoServiceImpl(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
        this.modelMapper = new ModelMapper();
    }

    public PuestoDto mappedPuestoDto(Puesto puesto){
        return modelMapper.map(puesto, PuestoDto.class);
    }

    public Puesto mappedPuesto(PuestoDto puestoDto){
        return modelMapper.map(puestoDto,Puesto.class);
    }


    @Override
    public List<PuestoDto> listaPuesto() {
        List<Puesto> puestoList = (List<Puesto>) puestoRepository.findAll();
        List<PuestoDto> puestoDtoList = new ArrayList<>();
        for (Puesto puesto: puestoList){
            puestoDtoList.add(mappedPuestoDto(puesto));
        }
        return puestoDtoList;
    }

    @Override
    public PuestoDto buscarPuesto(Integer id) {
        Optional<Puesto> puestoOptional = puestoRepository.findById(id);

        if (puestoOptional.isPresent()){
            Puesto puestoTemp = puestoOptional.get();
            PuestoDto puestoDto = mappedPuestoDto(puestoTemp);
            return puestoDto;
        }else {
            throw new NotFoundException("No se encontro el puesto");
        }
    }

    @Override
    public PuestoDto agregarPuesto(PuestoDto puestoDto) {
        Optional<Puesto> puestoOptional = puestoRepository.findById(puestoDto.getNumeroPuesto());

        if(!puestoOptional.isPresent()){
            Puesto puesto = mappedPuesto(puestoDto);
            puestoRepository.save(puesto);
            puestoDto.setNumeroPuesto(puesto.getNumeroPuesto());
        }else {
            throw new NotFoundException("No se permiten identificadores repetidos");
        }
        return puestoDto;
    }

    @Override
    public void editarPuesto(PuestoDto puestoDto) {
        Optional<Puesto> puestoOptional =  puestoRepository.findById(puestoDto.getNumeroPuesto());
        if(puestoOptional.isPresent()){
            Puesto puestoTemp = puestoOptional.get();
            if(!puestoTemp.getPuestoDescripcion().equals(puestoDto.getPuestoDescripcion())){
                puestoTemp.setPuestoDescripcion(puestoDto.getPuestoDescripcion());
            }
            puestoRepository.save(puestoTemp);
        }else{
            throw new NotFoundException("No se encontro el puesto");
        }

    }

    @Override
    public void borrarPuesto(Integer id) {

        if(puestoRepository.existsById(id)){
            puestoRepository.deleteById(id);
        }else {
            throw new NotFoundException("No se encontro el puesto");
        }
    }
}
