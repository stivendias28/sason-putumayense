package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.IdentificationType;
import backend.sasonptumayense.repository.IdentificationTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class identificationTypeService {
    private final IdentificationTypeRepository identificationTypeRepository;

    public IdentificationType getIdentificationTypeById(Integer id) {
        IdentificationType identificationType = identificationTypeRepository.findById(id).orElse(null);
        if (identificationType != null) {
            return identificationType;
        }
        return null;
    }

    public List<IdentificationType> getAllIdentificationTypes() {
        return identificationTypeRepository.findAll();
    }

}
