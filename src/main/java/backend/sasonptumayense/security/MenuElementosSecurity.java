package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.MenuElementoController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.MenuElementoRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuElementosSecurity extends GlobalMappingConfig {
    private final MenuElementoController menuElementoController;

    @GetMapping("menu-elementos")
    public ResponseEntity<ApiResponse> getMenuElementos(@RequestParam(name = "menuId", required = false) String menuId, @RequestParam(name = "elementoId", required = false) String elementoId) {
        return menuElementoController.getAllMenuElementos(menuId, elementoId);
    }

    @PostMapping("menu-elementos")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> saveMenuElementos(MenuElementoRequest request) {
        System.out.println("Request: " + request);
        return menuElementoController.createMenuElemento(request);
    }

    @DeleteMapping("menu-elementos/{id}")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> deleteMenuElementos(@PathVariable Integer id) {
        return menuElementoController.deleteById(id);
    }
}
