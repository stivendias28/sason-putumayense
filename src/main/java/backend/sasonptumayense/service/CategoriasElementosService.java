package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.CategoriasElementos;
import backend.sasonptumayense.repository.CategoriasElementosRepository;
import backend.sasonptumayense.request.CategoriaElementosRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriasElementosService {

    private final CategoriasElementosRepository categoriasElementosRepository;

    public CategoriasElementos getFindByName(String name) {
        CategoriasElementos categoriasElementos = categoriasElementosRepository.findByName(name).orElse(null);
        return (categoriasElementos != null) ? categoriasElementos : null;
    }

    public CategoriasElementos saveCategoriasElementos(CategoriaElementosRequest request) {

        CategoriasElementos categoriasElementos = CategoriasElementos.builder()
                .name(request.getName().toLowerCase())
                .build();
        return categoriasElementosRepository.save(categoriasElementos);
    }

    public List<CategoriasElementos> getAllCategoriasElementos() {
        return categoriasElementosRepository.findAll();
    }

    public CategoriasElementos getCategoriasElementosById(Integer id) {
        return categoriasElementosRepository.findById(id).orElse(null);
    }

    public CategoriasElementos deleteCategoriasElementos(Integer id) {
        CategoriasElementos categoriasElementos = categoriasElementosRepository.findById(id).orElse(null);
        categoriasElementosRepository.deleteById(id);
        return categoriasElementos;
    }


}
