package backend.sasonptumayense.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectedQuestionRequest {
    Integer userId;
    Integer questionId;
    String answer;
}
