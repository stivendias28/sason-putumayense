package backend.sasonptumayense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

}
