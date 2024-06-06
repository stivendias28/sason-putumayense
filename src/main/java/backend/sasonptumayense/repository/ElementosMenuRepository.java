package backend.sasonptumayense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.ElementosMenu;

@Repository
public interface ElementosMenuRepository extends JpaRepository<ElementosMenu, Integer> {
    Optional<ElementosMenu> findByName(String name);

    Optional<List<ElementosMenu>> findByCategoriasElementosId(Integer id);
}
