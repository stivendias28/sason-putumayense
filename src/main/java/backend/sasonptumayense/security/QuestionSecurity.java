package backend.sasonptumayense.security;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.QuestionController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.model.Question;
import backend.sasonptumayense.request.QuestionRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionSecurity extends GlobalMappingConfig {
	private final QuestionController questionController;
	
	@GetMapping(value = "questions")
	public List<Question> getQuestions() {
		return questionController.getAllQuestions();
	}

	@PostMapping("/questions")
	@Secured("ADMIN")
	public ResponseEntity<ApiResponse> saveQuestion(@RequestBody QuestionRequest question) {
		System.out.println("Question: " + question);
		return questionController.saveQuestion(question);
	}

	@DeleteMapping("/questions/{id}")
	@Secured("ADMIN")
	public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable Integer id) {
		return questionController.deleteQuestion(id);
	}
}
