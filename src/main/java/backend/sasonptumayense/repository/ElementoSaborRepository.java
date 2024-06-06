package backend.sasonptumayense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.ElementoSabor;

@Repository
public interface ElementoSaborRepository extends JpaRepository<ElementoSabor, Integer> {
    Void deleteByElementoId(Integer elementoId);
    void deleteBySaborId(Integer saborId);

    Optional<ElementoSabor> findByElementoIdAndSaborId(Integer elementoId, Integer saborId);

    Optional<List<ElementoSabor>> findBySaborId(Integer saborId);

    Optional<List<ElementoSabor>> findByElementoId(Integer elementoId);
}
