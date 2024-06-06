package backend.sasonptumayense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.SaboresPostres;

@Repository
public interface  SaboresPostresRepository extends JpaRepository<SaboresPostres, Integer> {
    Optional<SaboresPostres> findByName(String name);
}
