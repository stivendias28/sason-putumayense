package backend.sasonptumayense.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "selctedQuestions")
public class SelectedQuestion {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "selected_question_sequense")
	@SequenceGenerator(name = "selected_question_sequense", sequenceName = "selected_question_sequense_name", allocationSize = 1)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "questionId")
    private Question question;

    @Column(nullable = false)
    private String answer;
}
