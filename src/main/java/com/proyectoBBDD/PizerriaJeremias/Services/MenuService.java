package com.proyectoBBDD.PizerriaJeremias.Services;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;

import java.util.List;

public interface MenuService {

    public List<MenuDto> listarMenu();

    public MenuDto buscarMenu(Integer id);

    public MenuDto crearMenu(MenuDto menuDto);

    public void editarMenu(MenuDto menuDto);

    public void borrarMenu(Integer id);
}
