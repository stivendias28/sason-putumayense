package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.PedidosController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.request.PedidosRequest;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PedidosSecurity extends GlobalMappingConfig {
    private final PedidosController pedidosController;

    @GetMapping("/pedidos")
    public ResponseEntity<ApiResponse> getAllPedidos(
        @RequestParam(name = "userId", required = false) String userId, 
        @RequestParam(name = "estadoId", required = false) String estadoId, 
        @RequestParam(name = "fecha", required = false) String fecha) {
        return pedidosController.getAllPedidos(userId, estadoId, fecha);
    }

    @PostMapping("/pedidos")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> createPedidos(PedidosRequest request) {
        return pedidosController.createPedidos(request);
    }

    @DeleteMapping("/pedidos/{id}")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> deletePedidos(@PathVariable("id") String id) {
        return pedidosController.deletePedidos(Integer.parseInt(id));
    }


}
