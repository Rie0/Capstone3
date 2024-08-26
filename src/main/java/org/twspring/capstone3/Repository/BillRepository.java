package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.twspring.capstone3.Model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Optional<Bill> findBillById(Integer id);
}
