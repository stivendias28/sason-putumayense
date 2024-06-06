package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.CategoriasElementos;
import backend.sasonptumayense.model.ElementosMenu;
import backend.sasonptumayense.request.CategoriaElementosRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.service.CategoriasElementosService;
import backend.sasonptumayense.service.ElementosMenuService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoriasElementosController {
    private final CategoriasElementosService categoriasElementosService;
    private final ElementosMenuService elementosMenuService;

    public ResponseEntity<ApiResponse> getAllCategoriasElementos() {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", categoriasElementosService.getAllCategoriasElementos()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getCategoriasElementosById(Integer id) {
        CategoriasElementos categoriasElementos = categoriasElementosService.getCategoriasElementosById(id);
        return (categoriasElementos != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", categoriasElementos), HttpStatus.OK) 
            : new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND, "CategoriasElementos not found", null), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ApiResponse> saveCategoriasElementos(CategoriaElementosRequest request) {
        if(request == null || request.getName() == null || request.getName().isEmpty()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Name is required", null), HttpStatus.BAD_REQUEST);

        CategoriasElementos validate = categoriasElementosService.getFindByName(request.getName());
        if(validate != null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.CONFLICT, "'" + request.getName() + "' already exists", null), HttpStatus.CONFLICT);

        CategoriasElementos categoriasElementos = categoriasElementosService.saveCategoriasElementos(request);
        return (categoriasElementos != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", categoriasElementos), HttpStatus.OK) 
            : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ApiResponse> deleteCategoriasElementos(Integer id) {
        List<ElementosMenu> validate = elementosMenuService.getElementosMenuByCategoriasElementosId(id);
        if(!validate.isEmpty()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "CategoriasElementos has elements", null), HttpStatus.BAD_REQUEST);
        
        CategoriasElementos categoriasElementos = categoriasElementosService.getCategoriasElementosById(id);
        return (categoriasElementos != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Deleted", categoriasElementosService.deleteCategoriasElementos(id)), HttpStatus.OK) 
            : new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND, "CategoriasElementos not found", null), HttpStatus.NOT_FOUND);
    }

}
