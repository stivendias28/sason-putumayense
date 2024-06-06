package backend.sasonptumayense.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "elementoSabor")
public class ElementoSabor {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elemento_sabor_sequence")
	@SequenceGenerator(name = "elemento_sabor_sequence", sequenceName = "elemento_sabor_sequence_name", allocationSize = 1)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "elementoId", updatable = false, nullable = false)
    private ElementosMenu elemento;

    @ManyToOne
    @JoinColumn(name = "saborId", updatable = false, nullable = false)
    private SaboresPostres sabor;
}
