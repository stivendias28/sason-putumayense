package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.Gender;
import backend.sasonptumayense.repository.GenderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenderService {
    private final GenderRepository genderRepository;

    public Gender getGenderById(Integer id) {
        Gender gender = genderRepository.findById(id).orElse(null);
        if(gender != null) {
            return gender;
        }
        return null;
    }

    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }
}
