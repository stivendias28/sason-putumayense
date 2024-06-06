package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.ElementosMenu;
import backend.sasonptumayense.model.MenuElemento;
import backend.sasonptumayense.model.Menus;
import backend.sasonptumayense.repository.MenuElementoRepository;
import backend.sasonptumayense.request.MenuElementoRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuElementoService {
    private final MenuElementoRepository menuElementoRepository;
    private final MenusService menusService;
    private final ElementosMenuService elementosMenuService;

    public List<MenuElemento> getAllMenuElementos() {
        return menuElementoRepository.findAll();
    }

    public MenuElemento createMenuElemento(MenuElementoRequest request) {
        Menus menu = menusService.getMenusById(Integer.parseInt(request.getMenuId()));
        ElementosMenu elementosMenu = elementosMenuService.getById(Integer.parseInt(request.getElementoMenuId()));

        if(menu == null || elementosMenu == null) return null;

        MenuElemento menuElemento = MenuElemento.builder()
                .menus(menu)
                .elementosMenu(elementosMenu)
                .build();

        return menuElementoRepository.save(menuElemento);
    }

    public MenuElemento findByMenusIdAndElementosMenuId(Integer menusId, Integer elementosMenuId) {
        return menuElementoRepository.findByMenusIdAndElementosMenuId(menusId, elementosMenuId).orElse(null);
    }

    public List<MenuElemento> findByMenusId(Integer menusId) {
        return menuElementoRepository.findByMenusId(menusId).orElse(null);
    }

    public List<MenuElemento> findByElementosMenuId(Integer elementosMenuId) {
        return menuElementoRepository.findByElementosMenuId(elementosMenuId).orElse(null);
    }

    public MenuElemento deleteById(Integer id) {
        MenuElemento menuElemento = menuElementoRepository.findById(id).orElse(null);
        menuElementoRepository.deleteById(id);
        return menuElemento;
    }

    public MenuElemento findById(Integer id) {
        return menuElementoRepository.findById(id).orElse(null);
    }
}
