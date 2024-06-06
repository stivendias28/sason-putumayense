package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.EstadoPedido;
import backend.sasonptumayense.repository.EstadoPedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoPedidoService {
    private final EstadoPedidoRepository estadoPedidoRepository;

    public List<EstadoPedido> getAll() {
        return estadoPedidoRepository.findAll();
    }

    public EstadoPedido getById(Integer id) {
        return estadoPedidoRepository.findById(id).orElse(null);
    }
}
