package backend.sasonptumayense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.IdentificationType;

@Repository
public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Integer> {

}
