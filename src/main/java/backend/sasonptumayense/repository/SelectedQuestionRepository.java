package backend.sasonptumayense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.SelectedQuestion;

@Repository
public interface SelectedQuestionRepository extends JpaRepository<SelectedQuestion, Integer> {

}
