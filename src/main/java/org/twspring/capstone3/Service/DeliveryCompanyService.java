package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.Admin;
import org.twspring.capstone3.Model.DeliveryCompany;
import org.twspring.capstone3.Repository.AdminRepository;
import org.twspring.capstone3.Repository.DeliveryCompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryCompanyService {
    private final DeliveryCompanyRepository deliveryCompanyRepository;
    private final AdminRepository adminRepository;

    public List<DeliveryCompany> getAllDeliveryCompanies() {
        return deliveryCompanyRepository.findAll();
    }

    //EP
    public void addDeliveryCompany(Integer adminId, DeliveryCompany deliveryCompany) {
        Admin admin = adminRepository.findAdminById(adminId);
        if (admin == null) {
            throw new ApiException("Admin not found");
        }
        deliveryCompanyRepository.save(deliveryCompany);
    }

    public void updateDeliveryCompany(Integer adminId, Integer deliveryCompanyId, DeliveryCompany deliveryCompany) {
        Admin admin = adminRepository.findAdminById(adminId);
        if (admin == null) {
            throw new ApiException("Admin not found");
        }
        DeliveryCompany existingDeliveryCompany = deliveryCompanyRepository.findById(deliveryCompanyId).orElseThrow(() ->
                new ApiException("Delivery Company with id " + deliveryCompanyId + " not found")
        );
        existingDeliveryCompany.setName(deliveryCompany.getName());
        existingDeliveryCompany.setTimeRange(deliveryCompany.getTimeRange());
        existingDeliveryCompany.setDeliveryFee(deliveryCompany.getDeliveryFee());
        deliveryCompanyRepository.save(existingDeliveryCompany);
    }

    public void deleteDeliveryCompany(Integer adminId, Integer deliveryCompanyId) {
        Admin admin = adminRepository.findAdminById(adminId);
        if (admin == null) {
            throw new ApiException("Admin not found");
        }
        DeliveryCompany existingDeliveryCompany = deliveryCompanyRepository.findById(deliveryCompanyId).orElseThrow(() ->
                new ApiException("Delivery Company with id " + deliveryCompanyId + " not found")
        );
        deliveryCompanyRepository.delete(existingDeliveryCompany);
    }
}
