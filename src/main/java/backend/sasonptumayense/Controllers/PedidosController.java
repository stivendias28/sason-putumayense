package backend.sasonptumayense.Controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.DetallePedido;
import backend.sasonptumayense.model.Pedidos;
import backend.sasonptumayense.request.PedidosRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.DetallePedidoService;
import backend.sasonptumayense.service.EstadoPedidoService;
import backend.sasonptumayense.service.PedidosService;
import backend.sasonptumayense.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PedidosController {
    private final PedidosService pedidosService;
    private final EstadoPedidoService estadoPedidoService;
    private final UserService userService;
    private final DetallePedidoService detallePedidoService;

    public ResponseEntity<ApiResponse> getAllPedidos(
        @RequestParam(name = "userId", required = false) String userId, 
        @RequestParam(name = "estadoId", required = false) String estadoId, 
        @RequestParam(name = "fecha", required = false) String fecha) {

        if ((estadoId != null  && !estadoId.isEmpty()) && (userId != null && !userId.isEmpty())) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", pedidosService.getAllEstadoPedidoIdAndUserId(Integer.parseInt(estadoId), Integer.parseInt(userId))), 
                HttpStatus.OK);
        }

        if (userId != null && !userId.isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", pedidosService.getPedidosByUserId(Integer.parseInt(userId))), 
                HttpStatus.OK);
        }


        if (estadoId != null && !estadoId.isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", pedidosService.getAllPedidosByEstadoPedido(Integer.parseInt(estadoId))), 
                HttpStatus.OK);
        }

        if (fecha != null && !fecha.isEmpty()) {
            if(!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return new ResponseEntity<ApiResponse>(
                    new ApiResponse(HttpStatus.BAD_REQUEST, "this date not valid", null),
                    HttpStatus.BAD_REQUEST
                );
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(fecha, formatter);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", pedidosService.getAllPedidosByCreateAt(date)), 
                HttpStatus.OK);
        }

        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", pedidosService.getAllPedidos()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> createPedidos(PedidosRequest request) {
        System.out.println(request);
        DynamicResponseErrors obj = new DynamicResponseErrors();

        if (request == null) obj.addError("request", "Request is required");

        if (request.getEstadoPedidoId() == null || request.getEstadoPedidoId().isEmpty()) obj.addError("estadoPedidoId", "estadoPedidoId is required");

        if (request.getUserId() == null || request.getUserId().isEmpty()) obj.addError("userId", "userId is required");

        if (request.getTotal() == null) obj.addError("total", "total is required");

        if (request.getTotal() == null ||request.getTotal().compareTo(BigDecimal.ZERO) <= 0) obj.addError("total", "total must be greater than 0");

        if (obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if (estadoPedidoService.getById(Integer.parseInt(request.getEstadoPedidoId())) == null) new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "estadoPedidoId not found", null), HttpStatus.BAD_REQUEST);

        if (userService.getUserById(Integer.parseInt(request.getUserId())) == null) new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "userId not found", null), HttpStatus.BAD_REQUEST);

        Pedidos pedidos = pedidosService.createPedidos(request);
        return (pedidos != null) ?
                new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", pedidos), HttpStatus.OK)
                : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<ApiResponse> deletePedidos(Integer id) {
        List<DetallePedido> detallePedido = detallePedidoService.findByMenusId(id);

        if(detallePedido.size() > 0) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.CONFLICT, "You cannot delete the menu because there are orders placed", null), HttpStatus.CONFLICT);  


        Pedidos pedidos = pedidosService.getPedidosById(id);
        return (pedidos != null) ?
                new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Deleted", pedidosService.deletePedidos(id)), HttpStatus.OK)
                : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
