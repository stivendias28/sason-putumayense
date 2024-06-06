package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.DetallesPedidoController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.DetallePedidoRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetallePedidoSecurity extends GlobalMappingConfig {
    private final DetallesPedidoController detallesPedidoController;

    @GetMapping("/detalles-pedidos")
    public ResponseEntity<ApiResponse> getDetallesPedidos(
        @RequestParam(name = "pedidoId", required = false) String pedidoId, 
        @RequestParam(name = "menuId", required = false) String menuId) {
        return detallesPedidoController.getDetallesPedidos(pedidoId, menuId);
    }

    @PostMapping("/detalles-pedidos")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> createDetallesPedidos(DetallePedidoRequest request) {
        return detallesPedidoController.createDetallesPedidos(request);
    }

    @GetMapping("/detalles-pedidos/{id}")
    public ResponseEntity<ApiResponse> getDetallesPedidosById(@PathVariable Integer id) {
        return detallesPedidoController.getDetallesPedidosById(id);
    }
}
