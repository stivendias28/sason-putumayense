package backend.sasonptumayense.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidosRequest {
    String estadoPedidoId;
    String userId;
    BigDecimal total;
}
