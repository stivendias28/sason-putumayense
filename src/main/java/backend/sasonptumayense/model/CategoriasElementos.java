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
@Table(name = "categoriasElementos")
public class CategoriasElementos {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_secuense")
	@SequenceGenerator(name = "category_secuense", sequenceName = "category_secuense_name", allocationSize = 1)
	private Integer id;

    @Column(nullable = false)
    private String name;

}
