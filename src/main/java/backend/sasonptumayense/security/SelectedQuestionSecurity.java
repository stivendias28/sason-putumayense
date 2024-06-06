package backend.sasonptumayense.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.SelectedQuestionController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.model.SelectedQuestion;
import backend.sasonptumayense.request.SelectedQuestionRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SelectedQuestionSecurity extends GlobalMappingConfig {
    private final SelectedQuestionController selectedQuestionController;

    @PostMapping("/selected-questions")
    public String saveSelectedQuestion(@RequestBody SelectedQuestionRequest selectedQuestionRequest){
        selectedQuestionController.saveSelectedQuestion(selectedQuestionRequest);
        return "Saved";
    }

    /*@etMapping("/selected-questions/{id}")
    public ApiResponse getSelectedQuestionById(@PathVariable Integer id){
        SelectedQuestion response = selectedQuestionController.getSelectedQuestionById(id);
        if(response == null){
            return ApiResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .message("Question not found")
                    .build();
        }
        return ApiResponse.builder()
                .code(HttpStatus.OK)
                .message("Question found")
                .data(response)
                .build();
    }*/

    @GetMapping("/selected-questions/{id}")
    public ResponseEntity<Object> getSelectedQuestionById(@PathVariable Integer id){
        try{
            SelectedQuestion response = selectedQuestionController.getSelectedQuestionById(id);
            if(response == null){
                return new ResponseEntity<Object>("Question not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }




}
