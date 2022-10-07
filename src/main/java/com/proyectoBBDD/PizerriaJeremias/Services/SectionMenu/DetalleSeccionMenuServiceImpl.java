package com.proyectoBBDD.PizerriaJeremias.Services.SectionMenu;

import com.proyectoBBDD.PizerriaJeremias.Dto.MenuDto;
import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.DetalleSeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Dto.SectionMenu.SeccionMenuDto;
import com.proyectoBBDD.PizerriaJeremias.Entities.Menu;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu.DetalleSeccionMenu;
import com.proyectoBBDD.PizerriaJeremias.Entities.SectionMenu.SeccionMenu;
import com.proyectoBBDD.PizerriaJeremias.Repository.MenuRepository;
import com.proyectoBBDD.PizerriaJeremias.Repository.SectionMenu.DetalleSeccionMenuRepository;
import com.proyectoBBDD.PizerriaJeremias.Repository.SectionMenu.SeccionMenuRepository;
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
public class DetalleSeccionMenuServiceImpl implements  DetalleSeccionMenuService {

    private final ModelMapper modelMapper;
    private final DetalleSeccionMenuRepository detalleSeccionMenuRepository;

    private final SeccionMenuServiceImpl seccionMenuServiceImpl;


    private final SeccionMenuRepository seccionMenuRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public DetalleSeccionMenuServiceImpl(DetalleSeccionMenuRepository detalleSeccionMenuRepository, SeccionMenuServiceImpl seccionMenuServiceImpl, SeccionMenuRepository seccionMenuRepository, MenuRepository menuRepository){
        this.detalleSeccionMenuRepository = detalleSeccionMenuRepository;
        this.seccionMenuServiceImpl = seccionMenuServiceImpl;
        this.seccionMenuRepository = seccionMenuRepository;
        this.menuRepository = menuRepository;
        this.modelMapper = new ModelMapper();
    }


    public DetalleSeccionMenuDto mappedDetalleSeccionMenuDto(DetalleSeccionMenu detalleSeccionMenu){
        return modelMapper.map(detalleSeccionMenu,DetalleSeccionMenuDto.class);
    }

    public DetalleSeccionMenu mappedDetalleSeccionMenu(DetalleSeccionMenuDto detalleSeccionMenuDto){
        return modelMapper.map(detalleSeccionMenuDto,DetalleSeccionMenu.class);
    }


    @Override
    public List<DetalleSeccionMenuDto> listaDetalleSeccionMenu() {
        List<DetalleSeccionMenu> detalleSeccionMenuList = (List<DetalleSeccionMenu>) detalleSeccionMenuRepository.findAll();
        List<DetalleSeccionMenuDto> detalleSeccionMenuDtoList = new ArrayList<>();
        for(DetalleSeccionMenu detalleSeccionMenu : detalleSeccionMenuList){

            DetalleSeccionMenuDto detalleSeccionMenuDtoTemp = mappedDetalleSeccionMenuDto(detalleSeccionMenu);
            detalleSeccionMenuDtoTemp.setMenuNumero(detalleSeccionMenu.getSeccionMenu().getMenu().getNumeroMenu());
            detalleSeccionMenuDtoTemp.setSeccionMenuNumero(detalleSeccionMenu.getSeccionMenu().getNumeroSeccionMenu());

            detalleSeccionMenuDtoList.add(detalleSeccionMenuDtoTemp);
        }
        return detalleSeccionMenuDtoList;
    }

    @Override
    public DetalleSeccionMenuDto buscarDetalleSeccionMenu(Integer id) {

        Optional<DetalleSeccionMenu> detalleSeccionMenuOptional = detalleSeccionMenuRepository.findById(id);

        if(detalleSeccionMenuOptional.isPresent()){
            DetalleSeccionMenu detalleSeccionMenuTemp = detalleSeccionMenuOptional.get();
            DetalleSeccionMenuDto detalleSeccionMenuDto = mappedDetalleSeccionMenuDto(detalleSeccionMenuTemp);
            detalleSeccionMenuDto.setSeccionMenuNumero(detalleSeccionMenuTemp.getSeccionMenu().getNumeroSeccionMenu());
            detalleSeccionMenuDto.setMenuNumero(detalleSeccionMenuTemp.getSeccionMenu().getMenu().getNumeroMenu());
            return detalleSeccionMenuDto;

        }else {
            throw new NotFoundException("No se encotro el detalle de la seccion del menu");
        }
        /*
        DetalleSeccionMenu detalleSeccionMenu = detalleSeccionMenuRepository.findById(id).orElse(null);

        DetalleSeccionMenuDto detalleSeccionMenuDtoTemp = mappedDetalleSeccionMenuDto(detalleSeccionMenu);

        detalleSeccionMenuDtoTemp.setNumeroMenu(detalleSeccionMenu.getSeccionMenu().getMenu().getNumeroMenu());
        detalleSeccionMenuDtoTemp.setNumeroseccionMenu(detalleSeccionMenu.getSeccionMenu().getNumeroSeccionMenu());

        return detalleSeccionMenuDtoTemp;*/
    }

    @Override
    public DetalleSeccionMenuDto agregarDetalleSeccionMenu(DetalleSeccionMenuDto detalleSeccionMenuDto) {

        DetalleSeccionMenu detalleSeccionMenu = mappedDetalleSeccionMenu(detalleSeccionMenuDto);
        Optional<SeccionMenu> seccionMenu = seccionMenuRepository.findById(detalleSeccionMenuDto.getSeccionMenuNumero());

        if (!seccionMenu.isPresent()){
            throw  new NotFoundException("No se encontraron datos del secion menu");
        }
        log.error("ANTES DE SETEAR LA SECCION DEL MENU"+detalleSeccionMenu.toString());
        log.info(seccionMenu.get().toString());
        detalleSeccionMenu.setSeccionMenu(seccionMenu.get());
        log.error("DESPUES DE SETEAR LA SECCION DEL MENU"+ detalleSeccionMenu.toString());
        detalleSeccionMenuRepository.save(detalleSeccionMenu);

        detalleSeccionMenuDto.setSeccionMenuNumero(detalleSeccionMenu.getSeccionMenu().getNumeroSeccionMenu());
        detalleSeccionMenuDto.setMenuNumero(detalleSeccionMenu.getSeccionMenu().getMenu().getNumeroMenu());
        return  detalleSeccionMenuDto;




        // -----------------------------------------version Actualizada-----------------------------
        /*
        //la clase mappeada del dto
        DetalleSeccionMenu detalleSeccionMenu = mappedDetalleSeccionMenu(detalleSeccionMenuDto);

        //optional de la clase
        Optional<DetalleSeccionMenu> detalleSeccionMenuOptional = detalleSeccionMenuRepository.findById(detalleSeccionMenuDto.getDetalleSeccionNumero());


        log.error("----detalle sin el seccion menu"+detalleSeccionMenu.toString());

        //la condicion
        if(!detalleSeccionMenuOptional.isPresent()){

            //dto de la clase superrior
            //SeccionMenuDto seccionMenuDto = seccionMenuServiceImpl.buscarSeccionMenu(detalleSeccionMenuDto.getSeccionMenuNumero());

            //optional de la clase superior
            Optional<SeccionMenu> seccionMenuOptional = seccionMenuRepository.findById(detalleSeccionMenuDto.getSeccionMenuNumero());


            if(seccionMenuOptional.isPresent()){
                //SeccionMenu seccionMenu = seccionMenuOptional.get();



                log.error("+++clase seccion optional"+seccionMenuOptional.get());
                //setea la clase superior
                detalleSeccionMenu.setSeccionMenu(seccionMenuOptional.get());
                log.error("---detalle con la seccion menu"+detalleSeccionMenu.toString());
                detalleSeccionMenuRepository.save(detalleSeccionMenu);


                detalleSeccionMenuDto.setSeccionMenuNumero(detalleSeccionMenu.getSeccionMenu().getNumeroSeccionMenu());
                detalleSeccionMenuDto.setMenuNumero(detalleSeccionMenu.getSeccionMenu().getMenu().getNumeroMenu());
                return  detalleSeccionMenuDto;
            }else{
                throw new NotFoundException("No se encontro la seccion del menu");
            }
        }else{
            throw  new NotFoundException("No se permite identificadores repetidos");
        }
         */


        //-------------------------------------VERSION VIEJA--------------------------------
        /*
        Optional<SeccionMenu> seccionMenu = seccionMenuRepository.findById(detalleSeccionMenuDto.getNumeroseccionMenu());

        if(seccionMenu.isEmpty()){
            detalleSeccionMenuDto.setCodigoError(1);
            detalleSeccionMenuDto.setMensajeError("No se encontro la seccion del menu");
            return detalleSeccionMenuDto;
        }

        DetalleSeccionMenu detalleSeccionMenu = mappedDetalleSeccionMenu(detalleSeccionMenuDto);
        SeccionMenuDto seccionMenuDto = seccionMenuServiceImpl.buscarSeccionMenu(detalleSeccionMenuDto.getNumeroseccionMenu());


        if(seccionMenuDto.getCodigoError()!=0){
            detalleSeccionMenuDto.setCodigoError(seccionMenuDto.getCodigoError());
            detalleSeccionMenuDto.setMensajeError(seccionMenuDto.getMensajeError());
            return detalleSeccionMenuDto;
        }

        detalleSeccionMenu.setSeccionMenu(seccionMenuServiceImpl.mappedSeccionMenu(seccionMenuDto));

        DetalleSeccionMenu detalleSeccionMenuSave = null;
        if(detalleSeccionMenu.validarDatos()) {
            detalleSeccionMenuSave= detalleSeccionMenuRepository.save(detalleSeccionMenu);
        }

        if(detalleSeccionMenuSave!=null) {
            detalleSeccionMenuDto.setCodigoError(0);
            detalleSeccionMenuDto.setDetalleSeccionNumero(detalleSeccionMenuSave.getNumeroDetalleSeccion());
            detalleSeccionMenuDto.setMensajeError("Guardado Correctamente");
            return detalleSeccionMenuDto;
        }

        detalleSeccionMenuDto.setCodigoError(1);
        detalleSeccionMenuDto.setMensajeError("No se pudo guardar el detalle seccion Menu");
        return detalleSeccionMenuDto;*/
    }

    @Override
    public void editarDetalleSeccionMenu(DetalleSeccionMenuDto detalleSeccionMenuDto) {

        Optional<DetalleSeccionMenu> detalleSeccionMenu = detalleSeccionMenuRepository.findById(detalleSeccionMenuDto.getDetalleSeccionNumero());

        if(detalleSeccionMenu.isPresent()){
            DetalleSeccionMenu detalleSeccionMenuTemp = detalleSeccionMenu.get();
            if(!detalleSeccionMenuTemp.getPrecio().equals(detalleSeccionMenuDto.getPrecio())){
                detalleSeccionMenuTemp.setPrecio(detalleSeccionMenuDto.getPrecio());
            }
            if(detalleSeccionMenuTemp.getSeccionMenu().getMenu().getNumeroMenu().equals(detalleSeccionMenuDto.getMenuNumero())){
                Optional<Menu> menu = menuRepository.findById(detalleSeccionMenuDto.getMenuNumero());
                menu.ifPresent(detalleSeccionMenuTemp.getSeccionMenu()::setMenu);
            }
            if(detalleSeccionMenuTemp.getSeccionMenu().getNumeroSeccionMenu().equals(detalleSeccionMenuDto.getSeccionMenuNumero())){
                Optional<SeccionMenu> seccionMenu = seccionMenuRepository.findById(detalleSeccionMenuDto.getSeccionMenuNumero());
                seccionMenu.ifPresent(detalleSeccionMenuTemp::setSeccionMenu);
            }
            detalleSeccionMenuRepository.save(detalleSeccionMenuTemp);
        }else {
            throw  new NotFoundException("No se pudo encontrar el detalle de la seccion");
        }
    }

    @Override
    public void borrarDetalleSeccionMenu(Integer id) {
        if(detalleSeccionMenuRepository.existsById(id)){
            detalleSeccionMenuRepository.deleteById(id);
        }else {
            throw  new NotFoundException("No se pudo encontrar el detalle de la seccion");
        }
    }
}
