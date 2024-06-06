package backend.sasonptumayense.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.EstadoPedido;
import backend.sasonptumayense.model.Pedidos;
import backend.sasonptumayense.model.User;
import backend.sasonptumayense.repository.PedidosRepository;
import backend.sasonptumayense.request.PedidosRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidosService {
    private final PedidosRepository pedidosRepository;
    private final EstadoPedidoService estadoPedidoService;
    private final UserService userService;

    public List<Pedidos> getAllPedidos() {
        return pedidosRepository.findAll();
    }

    public Pedidos getPedidosById(Integer id) {
        return pedidosRepository.findById(id).orElse(null);
    }

    public List<Pedidos> getPedidosByUserId(Integer id) {
        return pedidosRepository.findByUserId(id).orElse(null);
    }

    public List<Pedidos> getAllEstadoPedidoIdAndUserId(Integer estadoPedidoId, Integer userId) {
        return pedidosRepository.findByEstadoPedidoIdAndUserId(estadoPedidoId, userId).orElse(null);
    }

    public List<Pedidos> getAllPedidosByEstadoPedido(Integer estadoPedidoId) {
        return pedidosRepository.findByEstadoPedidoId(estadoPedidoId).orElse(null);
    }

    public List<Pedidos> getAllPedidosByCreateAt(Date date) {
        return pedidosRepository.findByCreatedAt(date).orElse(null);
    }

    public Pedidos createPedidos(PedidosRequest request) {
        EstadoPedido estadoPedido = estadoPedidoService.getById(Integer.parseInt(request.getEstadoPedidoId()));
        User user = userService.getUserById(Integer.parseInt(request.getUserId()));

        if(estadoPedido == null || user == null) return null;

        Pedidos pedidos = Pedidos.builder()
                .estadoPedido(estadoPedido)
                .user(user)
                .total(request.getTotal())
                .build();

        return pedidosRepository.save(pedidos);
    }

    public Pedidos deletePedidos(Integer id) {
        Pedidos pedidos = pedidosRepository.findById(id).orElse(null);
        pedidosRepository.deleteById(id);
        return pedidos;
    }
}
