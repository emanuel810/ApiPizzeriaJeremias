package com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;
import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Entities.Menu;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu.SeccionMenu;
import com.proyectoBBDD.PizerriaJeremias.Repository.SectionMenu.SeccionMenuRepository;
import com.proyectoBBDD.PizerriaJeremias.Repository.MenuRepository;
import com.proyectoBBDD.PizerriaJeremias.Services.MenuServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeccionMenuServiceImpl implements  SeccionMenuService{

    private final SeccionMenuRepository seccionMenuRepository;
    private final ModelMapper modelMapper;

    private final MenuServiceImpl menuServiceImpl;

    private final MenuRepository menuRepository;

    @Autowired
    public SeccionMenuServiceImpl(SeccionMenuRepository seccionMenuRepository, MenuServiceImpl menuServiceImpl, MenuRepository menuRepository){
        this.seccionMenuRepository = seccionMenuRepository;
        this.menuServiceImpl = menuServiceImpl;
        this.menuRepository = menuRepository;
        modelMapper = new ModelMapper();
    }

    public SeccionMenuDto mappedSeccionMenuDto(SeccionMenu seccionMenu){
        return modelMapper.map(seccionMenu,SeccionMenuDto.class);
    }

    public SeccionMenu mappedSeccionMenu(SeccionMenuDto seccionMenuDto){
        return  modelMapper.map(seccionMenuDto, SeccionMenu.class);
    }

    @Override
    public List<SeccionMenuDto> listaSeccionMenu() {

        List<SeccionMenu> seccionMenus = (List<SeccionMenu>) seccionMenuRepository.findAll();
        List<SeccionMenuDto> seccionMenuDtos =  new ArrayList<>();
        for(SeccionMenu seccionMenu : seccionMenus){
            SeccionMenuDto seccionMenuDtoTemp = mappedSeccionMenuDto(seccionMenu);
            seccionMenuDtoTemp.setNumeroMenu(seccionMenu.getMenu().getMenuNumero());
            seccionMenuDtos.add(seccionMenuDtoTemp);
        }

        return seccionMenuDtos;
    }

    @Override
    public SeccionMenuDto buscarSeccionMenu(Integer id) {

        SeccionMenu seccionMenu =seccionMenuRepository.findById(id).orElse(null);
        SeccionMenuDto seccionMenuDto = mappedSeccionMenuDto(seccionMenu);
        seccionMenuDto.setNumeroMenu(seccionMenu.getMenu().getMenuNumero());

        return seccionMenuDto;
    }

    @Override
    public SeccionMenuDto agregarSeccionMenu(SeccionMenuDto seccionMenuDto) {

        //Verificar que no se un id repetido
        Optional<SeccionMenu> idSeccionMenu = seccionMenuRepository.findById(seccionMenuDto.getSeccionMenuNumero());

        if(idSeccionMenu.isPresent()){
            seccionMenuDto.setCodigoError(1);
            seccionMenuDto.setMensajeError("Id repetido");
            return seccionMenuDto;
        }

        //pasar de seccionMenuDto a seccionMenu
        SeccionMenu seccionMenu = mappedSeccionMenu(seccionMenuDto);
        //Buscar el menu
        MenuDto menuDto = menuServiceImpl.buscarMenu(seccionMenu.getMenu().getMenuNumero());

        //Si no encuentra el menu entrara a esta condicion
        if(menuDto.getCodigoError()!=0){
            seccionMenuDto.setCodigoError(menuDto.getCodigoError());
            seccionMenuDto.setMensajeError(menuDto.getMensajeError());
            return seccionMenuDto;
        }

        //recibe la informacion del menu y lo mapea
        seccionMenu.setMenu(menuServiceImpl.mappedMenu(menuDto));

        //crea una instancia temporal del menu
        SeccionMenu seccionMenuSave = null;

        //consulta los datos de que todos esten requeridos
        if (seccionMenu.validarDatos()){
            seccionMenuSave = seccionMenuRepository.save(seccionMenu);
        }

        //revisa si la instancia temporal esta con datos
        if(seccionMenuSave!=null){
            seccionMenuDto.setCodigoError(0);
            seccionMenuDto.setMensajeError("Se guardo corectamente");
            seccionMenuDto.setSeccionMenuNumero(seccionMenuSave.getSeccionMenuNumero());
            return seccionMenuDto;
        }

        //si no tiene datos la instancia temporal
        seccionMenuDto.setCodigoError(1);
        seccionMenuDto.setMensajeError("No se guardo el dato");
        return seccionMenuDto;
    }

    @Override
    public void editarSeccionMenu(SeccionMenuDto seccionMenuDto) {

        Optional<SeccionMenu> seccionMenu = seccionMenuRepository.findById(seccionMenuDto.getSeccionMenuNumero());

        if(seccionMenu.isPresent()){
            SeccionMenu seccionMenuTemp = seccionMenu.get();
            if(!seccionMenuTemp.getSeccionMenuNumero().equals(seccionMenuDto.getSeccionMenuNumero())){
                seccionMenuTemp.setSeccionMenuNumero(seccionMenuDto.getSeccionMenuNumero());
            }
            if (!seccionMenuTemp.getSeccinMenuDescripcion().equals(seccionMenuDto.getSeccinMenuDescripcion())){
                seccionMenuTemp.setSeccinMenuDescripcion(seccionMenuDto.getSeccinMenuDescripcion());
            }
            if(!seccionMenuTemp.getMenu().getMenuNumero().equals(seccionMenuDto.getNumeroMenu())){
                Optional<Menu> menu = menuRepository.findById(seccionMenuDto.getNumeroMenu());
                menu.ifPresent(seccionMenuTemp::setMenu);
            }
            seccionMenuRepository.save(seccionMenuTemp);
        }
    }
    @Override
    public void borrarSeccionMenu(Integer id) {
        if(seccionMenuRepository.existsById(id)){
            seccionMenuRepository.deleteById(id);
        }
    }
}
