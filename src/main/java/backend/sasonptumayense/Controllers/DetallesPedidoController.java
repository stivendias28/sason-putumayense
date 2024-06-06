package backend.sasonptumayense.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.DetallePedido;
import backend.sasonptumayense.request.DetallePedidoRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.DetallePedidoService;
import backend.sasonptumayense.service.MenusService;
import backend.sasonptumayense.service.PedidosService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetallesPedidoController {
    private final DetallePedidoService detallesPedidoService;
    private final MenusService menusService;
    private final PedidosService pedidosService;

    public ResponseEntity<ApiResponse> getDetallesPedidos(
        @RequestParam(name = "pedidoId", required = false) String pedidoId, 
        @RequestParam(name = "menuId", required = false) String menuId) {
        
        if ((pedidoId != null && !pedidoId.isEmpty()) && (menuId != null && !menuId.isEmpty())) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", detallesPedidoService.findDetallePedidoByMenusIdAndPedidosId(Integer.parseInt(menuId), Integer.parseInt(pedidoId)))
                , HttpStatus.OK);
        }

        if (pedidoId != null && !pedidoId.isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", detallesPedidoService.findByPedidosId(Integer.parseInt(pedidoId)))
                , HttpStatus.OK);
        }

        if (menuId != null && !menuId.isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", detallesPedidoService.findByMenusId(Integer.parseInt(menuId)))
                , HttpStatus.OK);
        }

        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", detallesPedidoService.findAllDetallePedidos()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getDetallesPedidosById(Integer id) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", detallesPedidoService.findADetallePedidoById(id)), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> createDetallesPedidos(DetallePedidoRequest request) {
        DynamicResponseErrors obj = new DynamicResponseErrors();

        if (request == null) obj.addError("request", "Request is required.");

        if (request.getMenusId() == null || request.getMenusId().isEmpty()) obj.addError("menusId", "menusId is required.");

        if (request.getPedidosId() == null || request.getPedidosId().isEmpty()) obj.addError("pedidosId", "pedidosId is required.");

        if (request.getQuantity() == null) obj.addError("quantity", "quantity is required.");

        if (request.getQuantity() < 0) obj.addError("quantity", "quantity must be greater than 0.");

        if (obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if(pedidosService.getPedidosById(Integer.parseInt(request.getPedidosId())) == null) new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "pedidosId not found", null), HttpStatus.BAD_REQUEST);

        if(menusService.getMenusById(Integer.parseInt(request.getMenusId())) == null) new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "menusId not found", null), HttpStatus.BAD_REQUEST);

        DetallePedido detallePedido = detallesPedidoService.createDetallePedido(request);

        return (detallePedido != null) ?
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", detallePedido), HttpStatus.OK)
            : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);

        
    }
}
