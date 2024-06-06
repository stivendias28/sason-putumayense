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

import backend.sasonptumayense.Controllers.MenusController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.MenusRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenusSecurity extends GlobalMappingConfig {
    private final MenusController menusController;


    @GetMapping("/menus")
    public ResponseEntity<ApiResponse> getMenus() {
        return menusController.getAllMenus();
    }


    @GetMapping("/menus/{id}")
    public ResponseEntity<ApiResponse> getMenusById(@PathVariable Integer id) {
        return menusController.getMenusById(id);
    }

    @PostMapping("/menus")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> saveMenus(@ModelAttribute MenusRequest request, @RequestParam("photo") MultipartFile photo) {
        return menusController.saveMenus(request, photo);
    }

    @DeleteMapping("/menus/{id}")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> deleteMenus(@PathVariable Integer id) {
        return menusController.deleteMenus(id);
    }

}
