package com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;
import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Entities.Menu;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu.SeccionMenu;
import com.proyectoBBDD.PizerriaJeremias.Repository.SectionMenu.SeccionMenuRepository;
import com.proyectoBBDD.PizerriaJeremias.Repository.MenuRepository;
import com.proyectoBBDD.PizerriaJeremias.Services.MenuServiceImpl;
import com.proyectoBBDD.PizerriaJeremias.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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
            seccionMenuDtoTemp.setMenuNumero(seccionMenu.getMenu().getNumeroMenu());
            seccionMenuDtos.add(seccionMenuDtoTemp);
        }
        return seccionMenuDtos;
    }

    @Override
    public SeccionMenuDto buscarSeccionMenu(Integer id) {

        Optional<SeccionMenu> seccionMenuOptional = seccionMenuRepository.findById(id);

        if(seccionMenuOptional.isPresent()){
            SeccionMenu seccionMenuTemp = seccionMenuOptional.get();
            SeccionMenuDto seccionMenuDto = mappedSeccionMenuDto(seccionMenuTemp);
            seccionMenuDto.setMenuNumero(seccionMenuTemp.getMenu().getNumeroMenu());
            log.error(seccionMenuDto.toString());
            return  seccionMenuDto;
        }else{
            throw new NotFoundException("No se encontro la seccion del menu");
        }

        /*

        SeccionMenu seccionMenu =seccionMenuRepository.findById(id).orElse(null);
        SeccionMenuDto seccionMenuDto = mappedSeccionMenuDto(seccionMenu);
        seccionMenuDto.setMenuNumero(seccionMenu.getMenu().getNumeroMenu());

        return seccionMenuDto;*/
    }

    @Override
    public SeccionMenuDto agregarSeccionMenu(SeccionMenuDto seccionMenuDto) {

        //la clase mappeada del dto --
        SeccionMenu seccionMenu = mappedSeccionMenu(seccionMenuDto);

        //optional de la clase
        Optional<SeccionMenu> seccionMenuOptional = seccionMenuRepository.findById(seccionMenuDto.getSeccionMenuNumero());

        //la condicion
        if(!seccionMenuOptional.isPresent()){

            //dto de la clase superrior --
            MenuDto menuDto = menuServiceImpl.buscarMenu(seccionMenuDto.getMenuNumero());


            //optional de la clase superior
            Optional<Menu> menuOptional = menuRepository.findById(menuDto.getMenuNumero());

            if (menuOptional.isPresent()){

                //setea la clase superior --
                seccionMenu.setMenu(menuServiceImpl.mappedMenu(menuDto));

                log.error(seccionMenu.toString());
                seccionMenuRepository.save(seccionMenu);
                seccionMenuDto.setSeccionMenuNumero(seccionMenu.getNumeroSeccionMenu());
                return seccionMenuDto;
            }else{
                throw new NotFoundException("No se encontro el menu");
            }

        }else {
            throw new NotFoundException("No se permite identificadores repetidos");
        }


        /*
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
        MenuDto menuDto = menuServiceImpl.buscarMenu(seccionMenuDto.getMenuNumero());

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
            seccionMenuDto.setSeccionMenuNumero(seccionMenuSave.getNumeroSeccionMenu());
            return seccionMenuDto;
        }

        //si no tiene datos la instancia temporal
        seccionMenuDto.setCodigoError(1);
        seccionMenuDto.setMensajeError("No se guardo el dato");
        return seccionMenuDto;*/
    }

    @Override
    public void editarSeccionMenu(SeccionMenuDto seccionMenuDto) {

        Optional<SeccionMenu> seccionMenuOptional = seccionMenuRepository.findById(seccionMenuDto.getSeccionMenuNumero());

        if(seccionMenuOptional.isPresent()){
            SeccionMenu seccionMenuTemp = seccionMenuOptional.get();
            if(!seccionMenuTemp.getNumeroSeccionMenu().equals(seccionMenuDto.getSeccionMenuNumero())){
                seccionMenuTemp.setNumeroSeccionMenu(seccionMenuDto.getSeccionMenuNumero());
            }
            if (!seccionMenuTemp.getSeccionMenuDescripcion().equals(seccionMenuDto.getSeccionMenuDescripcion())){
                seccionMenuTemp.setSeccionMenuDescripcion(seccionMenuDto.getSeccionMenuDescripcion());
            }
            if(!seccionMenuTemp.getMenu().getNumeroMenu().equals(seccionMenuDto.getMenuNumero())){
                Optional<Menu> menuOptional = menuRepository.findById(seccionMenuDto.getMenuNumero());
                menuOptional.ifPresent(seccionMenuTemp::setMenu);
            }
            seccionMenuRepository.save(seccionMenuTemp);
        }else{
            throw new NotFoundException("Seccion del menu no encontrado");
        }


        /*
        Optional<SeccionMenu> seccionMenu = seccionMenuRepository.findById(seccionMenuDto.getSeccionMenuNumero());

        if(seccionMenu.isPresent()){
            SeccionMenu seccionMenuTemp = seccionMenu.get();
            if(!seccionMenuTemp.getNumeroSeccionMenu().equals(seccionMenuDto.getSeccionMenuNumero())){
                seccionMenuTemp.setNumeroSeccionMenu(seccionMenuDto.getSeccionMenuNumero());
            }
            if (!seccionMenuTemp.getSeccionMenuDescripcion().equals(seccionMenuDto.getSeccionMenuDescripcion())){
                seccionMenuTemp.setSeccionMenuDescripcion(seccionMenuDto.getSeccionMenuDescripcion());
            }
            if(!seccionMenuTemp.getMenu().getNumeroMenu().equals(seccionMenuDto.getMenuNumero())){
                Optional<Menu> menu = menuRepository.findById(seccionMenuDto.getMenuNumero());
                menu.ifPresent(seccionMenuTemp::setMenu);
            }
            seccionMenuRepository.save(seccionMenuTemp);
        }*/
    }
    @Override
    public void borrarSeccionMenu(Integer id) {
        if(seccionMenuRepository.existsById(id)){
            seccionMenuRepository.deleteById(id);
        }else{
            throw new NotFoundException("Seccion del menu no encontrado");
        }
    }
}
