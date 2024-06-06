package backend.sasonptumayense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.Question;
import backend.sasonptumayense.repository.QuestionRepository;
import backend.sasonptumayense.request.QuestionRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question getQuestionById(Integer id) {
        Question question = questionRepository.findById(id).orElse(null);
        return (question != null) ? question : null;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question saveQuestion(QuestionRequest question) {
        Question newQuestion = Question.builder()
                .question(question.getQuestion().toLowerCase())
                .build();
        return (question != null) ? questionRepository.save(newQuestion) : null;
    }

    public Question getQuestionByQuestion(String question) {
        Optional<Question> response = questionRepository.findByQuestion(question);
        return (response != null) ? response.orElse(null) : null;
    }

    public Question deleteQuestion(Integer id) {
        Question question = questionRepository.findById(id).orElse(null);
        questionRepository.deleteById(id);
        return question;
    }
}
