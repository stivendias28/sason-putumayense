package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.Menus;
import backend.sasonptumayense.repository.MenusRepository;
import backend.sasonptumayense.request.MenusRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenusService {
    private final MenusRepository menusRepository;

    public List<Menus> getAllMenus(){
        return menusRepository.findAll();
    }

    public Menus getMenusById(Integer id){
        return menusRepository.findById(id).orElse(null);
    }

    public Menus getMenusByName(String name){
        return menusRepository.findByName(name).orElse(null);
    }

    public Menus createMenus(MenusRequest request, String pathPhoto) {
        Menus saveMenu = Menus.builder()
                .name(request.getName().toLowerCase())
                .description(request.getDescription())
                .photo_menu(pathPhoto)
                .build();
        return menusRepository.save(saveMenu);
    }

    public Menus deleteMenus(Integer id) {
        Menus menus = menusRepository.findById(id).orElse(null);
        if(menus != null) {
            menusRepository.delete(menus);
        }
        return menus;
    }
}

