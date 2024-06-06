package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.Question;
import backend.sasonptumayense.model.SelectedQuestion;
import backend.sasonptumayense.model.User;
import backend.sasonptumayense.request.SelectedQuestionRequest;
import backend.sasonptumayense.service.QuestionService;
import backend.sasonptumayense.service.SelectedQuestionService;
import backend.sasonptumayense.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SelectedQuestionController {
    private final SelectedQuestionService selectedQuestionService;
    private final UserService userService;
    private final QuestionService questionService;

    public List<SelectedQuestion> getAllSelectedQuestions() {
        return selectedQuestionService.getAllSelectedQuestions();
    }

    public SelectedQuestion saveSelectedQuestion(SelectedQuestionRequest selectedQuestion) {
        User user = userService.getUserById(selectedQuestion.getUserId());
        Question question = questionService.getQuestionById(selectedQuestion.getQuestionId());

        if(user == null || question == null) {
            return null;
        }

        SelectedQuestion saveData = SelectedQuestion.builder()
                                                .user(user)
                                                .question(question)
                                                .answer(selectedQuestion.getAnswer())
                                                .build();


        return selectedQuestionService.saveSelectedQuestion(saveData);
    }

    public void deleteSelectedQuestion(SelectedQuestion selectedQuestion) {
        selectedQuestionService.deleteSelectedQuestion(selectedQuestion);
    }

    public SelectedQuestion getSelectedQuestionById(Integer id) {
        return selectedQuestionService.getSelectedQuestionById(id);
    }

    public SelectedQuestion updateSelectedQuestion(SelectedQuestion selectedQuestion) {
        return selectedQuestionService.updateSelectedQuestion(selectedQuestion);
    }
}
