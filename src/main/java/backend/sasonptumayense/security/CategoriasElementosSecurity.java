package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.CategoriasElementosController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.CategoriaElementosRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoriasElementosSecurity extends GlobalMappingConfig {
    private final CategoriasElementosController categoriasElementosController;

    @GetMapping("categorias-elementos")
    public ResponseEntity<ApiResponse> getAllCategoriasElementos() {
        return categoriasElementosController.getAllCategoriasElementos();
    }

    @GetMapping("categorias-elementos/{id}")
    public ResponseEntity<ApiResponse> getCategoriasElementosById(@PathVariable Integer id) {
        return categoriasElementosController.getCategoriasElementosById(id);
    }

    @PostMapping("categorias-elementos")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> saveCategoriasElementos(@RequestBody CategoriaElementosRequest request) {
        return categoriasElementosController.saveCategoriasElementos(request);
    }

    @DeleteMapping("categorias-elementos/{id}")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> deleteCategoriasElementos(@PathVariable Integer id) {
        return categoriasElementosController.deleteCategoriasElementos(id);
    }


}
