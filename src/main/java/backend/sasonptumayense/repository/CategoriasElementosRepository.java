package backend.sasonptumayense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.CategoriasElementos;

@Repository
public interface CategoriasElementosRepository extends JpaRepository<CategoriasElementos, Integer> {
    Optional<CategoriasElementos> findByName(String name);
}  
