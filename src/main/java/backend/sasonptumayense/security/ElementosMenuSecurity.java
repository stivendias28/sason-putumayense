package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.sasonptumayense.Controllers.ElementosMenuController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.ElementosMenuRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ElementosMenuSecurity extends GlobalMappingConfig {
    private final ElementosMenuController elementosMenuController;

    @GetMapping("elementos-menu")
    public ResponseEntity<ApiResponse> getAllElementosMenu() {
        return elementosMenuController.getAllElementosMenu();
    }

    @PostMapping("elementos-menu")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> saveElementosMenu(@ModelAttribute ElementosMenuRequest request, @RequestParam("photo") MultipartFile photo) {
        return elementosMenuController.saveElementosMenu(request, photo);
    }

    @DeleteMapping("elementos-menu/{id}")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> deleteElementosMenu(@PathVariable Integer id) {
        return elementosMenuController.deleteElementosMenu(id);
    }
}
