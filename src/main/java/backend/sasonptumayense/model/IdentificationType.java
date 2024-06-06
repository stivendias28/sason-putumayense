package backend.sasonptumayense.model;

import jakarta.persistence.Basic;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "identificationsType")
public class IdentificationType {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idType_sequence")
	@SequenceGenerator(name = "idType_sequence", sequenceName = "idType_sequence_name", allocationSize = 1)
	private Integer id;

	@Basic
	
	@Column(nullable = false)
	private String nameIdentificationType;
	
	@Column(nullable = false)
	private String abrevIdentificationType;
}
