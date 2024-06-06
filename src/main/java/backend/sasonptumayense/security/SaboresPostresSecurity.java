package backend.sasonptumayense.security;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.SaboresPostresController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.model.SaboresPostres;
import backend.sasonptumayense.request.SaboresPostresRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SaboresPostresSecurity extends GlobalMappingConfig {
    private final SaboresPostresController saboresPostresController;

    @GetMapping("/sabores-postres")
    public List<SaboresPostres> getAllSaboresPostres() {
        return saboresPostresController.getAllSaboresPostres();
    }


    @GetMapping("/sabores-postres/{id}")
    @Secured("ADMIN")
    public ResponseEntity<Object> getSaboresPostresById(@PathVariable Integer id) {
        try {
            SaboresPostres response = saboresPostresController.getSaboresPostresById(id);
            return (response != null) ? new ResponseEntity<Object>(response, HttpStatus.OK) : new ResponseEntity<Object>("Question not found", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sabores-postres")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> saveSaboresPostres(@RequestBody SaboresPostresRequest request) {
        return saboresPostresController.saveSaboresPostres(request);
    }
}
