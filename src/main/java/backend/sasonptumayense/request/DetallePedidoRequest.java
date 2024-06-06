package backend.sasonptumayense.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoRequest {
    String pedidosId;
    String menusId;
    Integer quantity;
    String opcionesPerzonalidas;
}
