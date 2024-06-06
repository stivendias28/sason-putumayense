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
@Table(name = "detallePedidos", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class DetallePedido {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_pedido_secuense")
	@SequenceGenerator(name = "detalle_pedido_secuense", sequenceName = "detalle_pedido_secuense_name", allocationSize = 1)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedidosId", nullable = false, updatable = false)
    private Pedidos pedidos;

    @ManyToOne
    @JoinColumn(name = "MenuId", nullable = false, updatable = false)
    private Menus menus;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = true)
    private String optionsPersonalizeds;
}
