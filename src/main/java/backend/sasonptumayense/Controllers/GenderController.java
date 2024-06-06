package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.Gender;
import backend.sasonptumayense.service.GenderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GenderController{
    private final GenderService genderService;

    public List<Gender> getAllGenders() {
        return genderService.getAllGenders();
    }

    public Gender getGenderById(Integer id) {
        return genderService.getGenderById(id);
    }

}
