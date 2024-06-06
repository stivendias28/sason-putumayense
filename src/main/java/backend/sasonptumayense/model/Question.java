package backend.sasonptumayense.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "question"}))
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questions_seq")
    @SequenceGenerator(name = "questions_seq", sequenceName = "questions_seq_name", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String question;
}
