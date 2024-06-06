package backend.sasonptumayense.security;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.IdentificationTypeController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.model.IdentificationType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IdentificationTypeSecurity extends GlobalMappingConfig {
    private final IdentificationTypeController identificationTypeController;

    @GetMapping("/identification-types")
    public List<IdentificationType> getIdentificationTypes() {
        return identificationTypeController.getAllIdentificationTypes();
    }

    @GetMapping("/identification-types/{id}")
    public IdentificationType getIdentificationType(@PathVariable Integer id) {
        return identificationTypeController.getIdentificationTypeById(id);
    }
    
}
