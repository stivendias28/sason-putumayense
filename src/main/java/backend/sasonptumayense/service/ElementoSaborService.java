package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.ElementoSabor;
import backend.sasonptumayense.model.ElementosMenu;
import backend.sasonptumayense.model.SaboresPostres;
import backend.sasonptumayense.repository.ElementoSaborRepository;
import backend.sasonptumayense.request.ElementoSaborRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElementoSaborService {
    private final ElementoSaborRepository elementosSaborRepository;
    private final ElementosMenuService elementosMenuService;
    private final SaboresPostresService saboresPostresService;

    public List<ElementoSabor> getAllElementosSabores() {
        return elementosSaborRepository.findAll();
    }

    public List<ElementoSabor> findByElementoId(Integer elementoId) {
        return elementosSaborRepository.findByElementoId(elementoId).orElse(null);
    }

    public List<ElementoSabor> findBySaborId(Integer saborId) {
        return elementosSaborRepository.findBySaborId(saborId).orElse(null);
    }

    public ElementoSabor findByElementoIdAndSaborId(Integer elementoId, Integer saborId) {
        return elementosSaborRepository.findByElementoIdAndSaborId(elementoId, saborId).orElse(null);
    }

    public ElementoSabor createElementoSabor(ElementoSaborRequest request) {
        ElementosMenu elementosMenu = elementosMenuService.getById(Integer.parseInt(request.getElementoId()));
        SaboresPostres saboresPostres = saboresPostresService.getSaboresPostresById(Integer.parseInt(request.getSaborId()));

        if(elementosMenu == null || saboresPostres == null) return null;

        ElementoSabor elementoSabor = ElementoSabor.builder()
            .elemento(elementosMenu)
            .sabor(saboresPostres)
            .build();

        return elementosSaborRepository.save(elementoSabor);
    }

    public void deleteElementoSabor(Integer id) {
        elementosSaborRepository.deleteById(id);
    }

    public void deleteByElementoId(Integer elementoId) {
        elementosSaborRepository.deleteByElementoId(elementoId);
    }

    public void deleteBySaborId(Integer saborId) {
        elementosSaborRepository.deleteBySaborId(saborId);
    }

}
