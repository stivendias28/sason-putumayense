package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.SelectedQuestion;
import backend.sasonptumayense.repository.SelectedQuestionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectedQuestionService {
    private final SelectedQuestionRepository selectedQuestionRepository;

    public List<SelectedQuestion> getAllSelectedQuestions() {
        return selectedQuestionRepository.findAll();
    }

    public SelectedQuestion saveSelectedQuestion(SelectedQuestion selectedQuestion) {
        return selectedQuestionRepository.save(selectedQuestion);
    }

    public void deleteSelectedQuestion(SelectedQuestion selectedQuestion) {
        selectedQuestionRepository.delete(selectedQuestion);
    }

    public SelectedQuestion getSelectedQuestionById(Integer id) {
        return selectedQuestionRepository.findById(id).orElse(null);
    }

    public SelectedQuestion updateSelectedQuestion(SelectedQuestion selectedQuestion) {
        return selectedQuestionRepository.save(selectedQuestion);
    }

}
