package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.DeliveryCompany;

@Repository
public interface DeliveryCompanyRepository extends JpaRepository<DeliveryCompany, Integer> {
    DeliveryCompany getDeliveryCompaniesById(Integer id);
}
