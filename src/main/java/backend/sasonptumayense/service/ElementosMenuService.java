package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.CategoriasElementos;
import backend.sasonptumayense.model.ElementosMenu;
import backend.sasonptumayense.repository.ElementosMenuRepository;
import backend.sasonptumayense.request.ElementosMenuRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElementosMenuService {
    private final ElementosMenuRepository elementosMenuRepository;
    private final CategoriasElementosService categoriasElementosService;

    public List<ElementosMenu> getAll() {
        return elementosMenuRepository.findAll();
    }

    public ElementosMenu getById(Integer id) {
        return elementosMenuRepository.findById(id).orElse(null);
    }

    public ElementosMenu getByName(String name) {
        return elementosMenuRepository.findByName(name).orElse(null);
    }
    
    public ElementosMenu save(ElementosMenuRequest request, String pathPhoto) {
        CategoriasElementos categoriasElementos = categoriasElementosService.getCategoriasElementosById(Integer.parseInt(request.getCategoriasElementosId()));
        if(categoriasElementos == null) return null;

        ElementosMenu elementosMenu = ElementosMenu.builder()
                .name(request.getName())
                .description(request.getDescription())
                .categoriasElementos(categoriasElementos)
                .price(request.getPrice())
                .photoElemento(pathPhoto)
                .build();

        return (request != null) ? elementosMenuRepository.save(elementosMenu) : null;

    }

    public List<ElementosMenu> getElementosMenuByCategoriasElementosId(Integer categoriasElementosId) {
        return elementosMenuRepository.findByCategoriasElementosId(categoriasElementosId).orElse(null);
    }

    public ElementosMenu deleteElementosMenu(Integer id) {
        ElementosMenu elementosMenu = elementosMenuRepository.findById(id).orElse(null);
        elementosMenuRepository.deleteById(id);
        return elementosMenu;
    }
}
