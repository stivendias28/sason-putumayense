package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.Question;
import backend.sasonptumayense.request.QuestionRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.service.QuestionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController{
    private final QuestionService questionService;

    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    public Question getQuestionById(Integer id) {
        return questionService.getQuestionById(id);
    }

    public ResponseEntity<ApiResponse> saveQuestion(QuestionRequest question) {
        
        if(question.getQuestion() == null || question.getQuestion().isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.BAD_REQUEST, "Question is required", null),
                HttpStatus.BAD_REQUEST);
        }

        Question val = questionService.getQuestionByQuestion(question.getQuestion().toLowerCase());
        return (val != null) ? 
            new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.BAD_REQUEST, "Question already exists", null),
                HttpStatus.BAD_REQUEST) 
                : new ResponseEntity<ApiResponse>(
                    new ApiResponse(HttpStatus.OK, "Saved", questionService.saveQuestion(question)), 
                    HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> deleteQuestion(Integer id) {
        Question question = questionService.getQuestionById(id);
        return (question != null) ? 
            new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "Deleted", questionService.deleteQuestion(id)), 
                HttpStatus.OK) 
            : new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.NOT_FOUND, "Question not found", null), 
                HttpStatus.NOT_FOUND);
    }
}
