package backend.sasonptumayense.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genders")
public class Gender {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_sequence")
	@SequenceGenerator(name = "gender_sequence", sequenceName = "gender_sequence_name", allocationSize = 1)
	private Integer id;
	
	@Column(nullable = false)
	private String nameGender;
	
	@Column(nullable = false)
	private String abrevGender;	
}
