package com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;

import java.util.List;

public interface SeccionMenuService {

    public List<SeccionMenuDto> listaSeccionMenu();

    public SeccionMenuDto buscarSeccionMenu(Integer id);

    public SeccionMenuDto agregarSeccionMenu(SeccionMenuDto seccionMenuDto);

    public void editarSeccionMenu(SeccionMenuDto seccionMenuDto);

    public void borrarSeccionMenu(Integer id);
}
