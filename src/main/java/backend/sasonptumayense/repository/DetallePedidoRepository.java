package backend.sasonptumayense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository <DetallePedido, Integer> {
    void deleteByPedidosId(Integer pedidosId);

    void deleteByMenusId(Integer menusId);

    void deleteByPedidosIdAndMenusId(Integer pedidosId, Integer menusId);
    
    Optional<List<DetallePedido>> findByMenusId(Integer menusId);

    Optional<List<DetallePedido>> findByPedidosId(Integer pedidosId);

    Optional<DetallePedido> findByMenusIdAndPedidosId(Integer menusId, Integer pedidosId);



}
