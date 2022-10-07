package com.proyectoBBDD.PizerriaJeremias.Services.SectionRestaurante;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionRestaurante.PuestoDto;

import java.util.List;

public interface PuestoService {

    public List<PuestoDto> listaPuesto();

    public PuestoDto buscarPuesto(Integer id);

    public PuestoDto agregarPuesto(PuestoDto puestoDto);

    public void editarPuesto(PuestoDto puestoDto);

    public void borrarPuesto(Integer id);

}
