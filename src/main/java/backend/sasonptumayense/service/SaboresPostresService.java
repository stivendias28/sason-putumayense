package backend.sasonptumayense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.SaboresPostres;
import backend.sasonptumayense.repository.SaboresPostresRepository;
import backend.sasonptumayense.request.SaboresPostresRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaboresPostresService {
    private final SaboresPostresRepository saboresPostresRepository;

    public List<SaboresPostres> getAllSaboresPostres() {
        return saboresPostresRepository.findAll();
    }

    public SaboresPostres getSaboresPostresById(Integer id) {
        return saboresPostresRepository.findById(id).orElse(null);
    }

    public SaboresPostres saveSaboresPostres(SaboresPostresRequest request) {
        String name = request.getName();

        SaboresPostres saboresPostres = SaboresPostres.builder()
                .name(name)
                .build();
        return (request != null) ? saboresPostresRepository.save(saboresPostres) : null;
    }

    public void deleteSaboresPostresById(Integer id) {
        saboresPostresRepository.deleteById(id);
    }

    public SaboresPostres updateSaboresPostresById(Integer id, SaboresPostresRequest request) {
        String name = request.getName();
        SaboresPostres saboresPostres = saboresPostresRepository.findById(id).orElse(null);
        if (saboresPostres != null) {
            saboresPostres.setName(name);
            return saboresPostresRepository.save(saboresPostres);
        }
        return null;
    }

    public SaboresPostres getSaboresPostresByName(String name) {
        Optional<SaboresPostres> saboresPostres = saboresPostresRepository.findByName(name);
        return (saboresPostres != null) ? saboresPostres.orElse(null) : null;
    }

}
