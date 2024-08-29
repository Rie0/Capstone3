package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.ArtOrder;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<ArtOrder, Integer> {
    ArtOrder findOrderById(Integer id);
}
