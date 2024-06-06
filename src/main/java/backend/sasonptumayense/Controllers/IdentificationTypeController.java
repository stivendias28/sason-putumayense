package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.IdentificationType;
import backend.sasonptumayense.service.identificationTypeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IdentificationTypeController {
    private final identificationTypeService identificationTypeService;

    public List<IdentificationType> getAllIdentificationTypes(){
        return identificationTypeService.getAllIdentificationTypes();
    }

    public IdentificationType getIdentificationTypeById(Integer id) {
        IdentificationType identificationType = identificationTypeService.getIdentificationTypeById(id);
        if (identificationType != null) {
            return identificationType;
        }
        return null;
    }
}
