package backend.sasonptumayense.security;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.GenderController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.model.Gender;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GenderSecurity extends GlobalMappingConfig {
    private final GenderController genderController;

    @GetMapping("/genders")
    public List<Gender> getGenders() {
        return genderController.getAllGenders();
    }
}
