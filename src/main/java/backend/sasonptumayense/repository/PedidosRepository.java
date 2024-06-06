package backend.sasonptumayense.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

    Optional<List<Pedidos>> findByUserId(Integer userId);

    Optional<List<Pedidos>> findByEstadoPedidoIdAndUserId(Integer estadoPedidoId, Integer userId);

    Optional<List<Pedidos>> findByEstadoPedidoId(Integer estadoPedidoId);

    Optional<List<Pedidos>> findByCreatedAt(Date createdAt);

    void deleteByEstadoPedidoId(Integer estadoPedidoId);

    void deleteByUserId(Integer userId);
}
