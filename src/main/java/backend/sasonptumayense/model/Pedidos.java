package backend.sasonptumayense.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Pedidos {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidos")
	@SequenceGenerator(name = "pedidos", sequenceName = "pedidos_name", allocationSize = 1)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "estadoPedidoId")
    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Setter
    @Column(nullable = false, updatable = false)
    @Builder.Default
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
}
