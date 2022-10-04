package com.proyectoBBDD.PizerriaJeremias.Services;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;
import com.proyectoBBDD.PizerriaJeremias.Entities.Menu;
import com.proyectoBBDD.PizerriaJeremias.Repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{

    private final ModelMapper modeloObject;

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository){
        this.modeloObject = new ModelMapper();
        this.menuRepository = menuRepository;
    }

    public MenuDto mappedMenuDto(Menu menu) {

        return modeloObject.map(menu, MenuDto.class);
    }

    public Menu mappedMenu(MenuDto menuDto){
        return modeloObject.map(menuDto, Menu.class);
    }


    @Override
    public List<MenuDto> listarMenu() {
        List<Menu> menus = (List<Menu>) menuRepository.findAll();
        List<MenuDto> menuDtos = new ArrayList<>();
        for (Menu menu : menus) {
            menuDtos.add(mappedMenuDto(menu));
        }
        return menuDtos;
    }

    @Override
    public MenuDto buscarMenu(Integer id) {
        Menu menu = menuRepository.findById(id).orElse(null);
        MenuDto menuDto =new MenuDto();
        if(menu==null) {

            menuDto.setCodigoError(1);
            menuDto.setMensajeError("Menu no encontrado");
            return menuDto;
        }
        menuDto = mappedMenuDto(menu);
        menuDto.setCodigoError(0);
        return menuDto;
    }

    @Override
    public MenuDto agregarMenu(MenuDto menuDto) {
        Menu menu = mappedMenu(menuDto);

        Optional<Menu> idmenu = menuRepository.findById(menuDto.getMenuNumero());

        if(idmenu.isPresent()){
            menuDto.setCodigoError(1);
            menuDto.setMensajeError("Id repetido");
            return menuDto;
        }

        if(menu.validarDatos()) {
            Menu menuSave = menuRepository.save(menu);
            menuDto.setMenuNumero(menuSave.getMenuNumero());
            menuDto.setMensajeError("Guardado correctamente");
            menuDto.setCodigoError(0);
            return menuDto;
        }else {
            menuDto.setCodigoError(1);
            menuDto.setMensajeError("No es posible guardar la informacion solicitada");
            return menuDto;
        }
    }

    @Override
    public void editarMenu(MenuDto menuDto) {

        Optional<Menu> menu = menuRepository.findById(menuDto.getMenuNumero());

        if(menu.isPresent()){
            Menu menuObtenido = menu.get();
            if(!menuObtenido.getMenuDescripcion().equals(menuDto.getMenuDescripcion())){
                menuObtenido.setMenuDescripcion(menuDto.getMenuDescripcion());
            }
            menuRepository.save(menuObtenido);
        }
    }

    @Override
    public void borrarMenu(Integer id) {
        if(menuRepository.existsById(id)){
            menuRepository.deleteById(id);
        }
    }
}
