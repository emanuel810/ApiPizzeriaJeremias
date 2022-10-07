package com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.DetalleSeccionMenuDto;

import java.util.List;

public interface DetalleSeccionMenuService {

    public List<DetalleSeccionMenuDto> listaDetalleSeccionMenu();

    public DetalleSeccionMenuDto buscarDetalleSeccionMenu(Integer id);

    public DetalleSeccionMenuDto agregarDetalleSeccionMenu(DetalleSeccionMenuDto detalleSeccionMenuDto);

    public void editarDetalleSeccionMenu(DetalleSeccionMenuDto detalleSeccionMenuDto);

    public void borrarDetalleSeccionMenu(Integer id);
}
