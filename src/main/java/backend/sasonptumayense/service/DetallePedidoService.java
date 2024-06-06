package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.DetallePedido;
import backend.sasonptumayense.model.Menus;
import backend.sasonptumayense.model.Pedidos;
import backend.sasonptumayense.repository.DetallePedidoRepository;
import backend.sasonptumayense.request.DetallePedidoRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;
    private final MenusService menusService;
    private final PedidosService pedidosService;

    public List<DetallePedido> findAllDetallePedidos() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido findADetallePedidoById(Integer id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public List<DetallePedido> findByMenusId(Integer menusId) {
        return detallePedidoRepository.findByMenusId(menusId).orElse(null);
    }

    public List<DetallePedido> findByPedidosId(Integer pedidosId) {
        return detallePedidoRepository.findByPedidosId(pedidosId).orElse(null);
    }

    public DetallePedido findDetallePedidoByMenusIdAndPedidosId(Integer menusId, Integer pedidosId) {
        return detallePedidoRepository.findByMenusIdAndPedidosId(menusId, pedidosId).orElse(null);
    }

    public DetallePedido createDetallePedido(DetallePedidoRequest request) {
        Menus menus = menusService.getMenusById(Integer.parseInt(request.getMenusId()));
        Pedidos pedidos = pedidosService.getPedidosById(Integer.parseInt(request.getPedidosId()));

        if(menus == null || pedidos == null) return null;

        DetallePedido detallePedido = DetallePedido.builder()
                .menus(menus)
                .pedidos(pedidos)
                .quantity(request.getQuantity())
                .optionsPersonalizeds(request.getOpcionesPerzonalidas())
                .build();
        return detallePedidoRepository.save(detallePedido);
    }

    public void deletDetallePedidoByPedidoId(Integer pedidosId) {
        List<DetallePedido> detallePedido = detallePedidoRepository.findByPedidosId(pedidosId).orElse(null);
        if(detallePedido != null) {
            for(Object item: detallePedido) {
                Integer id = ((DetallePedido) item).getId();
                detallePedidoRepository.deleteByPedidosId(id);
            }
        }
    }
}
