package backend.sasonptumayense.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "elementosMenu", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class ElementosMenu {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elementoMenu_secuense")
	@SequenceGenerator(name = "elementoMenu_secuense", sequenceName = "elementoMenu_secuense_name", allocationSize = 1)
	private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoriasElementosId")
    private CategoriasElementos categoriasElementos;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String photoElemento; 
}
