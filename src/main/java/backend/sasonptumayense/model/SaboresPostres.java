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
@Table(name = "saborespostres", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class SaboresPostres {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saborespostres_id_seq")
    @SequenceGenerator(name = "saborespostres_id_seq", sequenceName = "saborespostres_id_seq_name", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    /*@Column(nullable = false)
    private String description;*/
}
