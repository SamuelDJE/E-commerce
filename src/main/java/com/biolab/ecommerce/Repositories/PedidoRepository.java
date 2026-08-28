package com.biolab.ecommerce.Repositories;

import com.biolab.ecommerce.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
