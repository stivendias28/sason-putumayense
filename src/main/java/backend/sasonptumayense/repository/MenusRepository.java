package backend.sasonptumayense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.Menus;

@Repository
public interface MenusRepository extends JpaRepository<Menus, Integer> {
    Optional<Menus> findByName(String name);

}
