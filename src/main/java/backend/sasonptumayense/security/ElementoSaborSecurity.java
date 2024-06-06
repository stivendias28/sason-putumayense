package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.ElementoSaborController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.ElementoSaborRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ElementoSaborSecurity extends GlobalMappingConfig {
    private final ElementoSaborController elementoSaborController;

    @GetMapping("/elementos-sabor")
    public ResponseEntity<ApiResponse> getelementoSabor(@RequestParam(name = "saborId", required = false) String saborId, @RequestParam(name = "elementoId", required = false) String elementoId) {
        return elementoSaborController.getAllElementoSabor(saborId, elementoId);
    }

    @PostMapping("/elementos-sabor")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> saveElementoSabor(@RequestBody ElementoSaborRequest request) {
        return elementoSaborController.createElementoSabor(request);
    }


}
