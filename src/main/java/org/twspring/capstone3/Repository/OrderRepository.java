package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.OrderArt;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderArt, Integer> {
    Optional<OrderArt> findOrderById(Integer id);
}
