package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.CommissionRequest;
@Repository
public interface CommissionRequestRepository extends JpaRepository<CommissionRequest, Integer> {
    CommissionRequest findCommissionRequestById(Integer id);
}
