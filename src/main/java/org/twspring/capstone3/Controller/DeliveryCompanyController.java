package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.DeliveryCompany;
import org.twspring.capstone3.Service.DeliveryCompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/delivery-company")
public class DeliveryCompanyController {
    private final DeliveryCompanyService deliveryCompanyService;

    @GetMapping("/get")
    public ResponseEntity getAllDeliveryCompanies() {
        return ResponseEntity.status(200).body(deliveryCompanyService.getAllDeliveryCompanies());
    }

    @PostMapping("/add/{adminId}")
    public ResponseEntity addDeliveryCompany(@PathVariable Integer adminId, @Valid @RequestBody DeliveryCompany deliveryCompany) {
        deliveryCompanyService.addDeliveryCompany(adminId, deliveryCompany);
        return ResponseEntity.status(200).body(new ApiResponse("Delivery Company added successfully"));
    }

    @PutMapping("/update/{adminId}/{deliveryCompanyId}")
    public ResponseEntity updateDeliveryCompany(@PathVariable Integer adminId, @PathVariable Integer deliveryCompanyId, @Valid @RequestBody DeliveryCompany deliveryCompany) {
        deliveryCompanyService.updateDeliveryCompany(adminId, deliveryCompanyId, deliveryCompany);
        return ResponseEntity.status(200).body(new ApiResponse("Delivery Company updated successfully"));
    }

    @DeleteMapping("/delete/{adminId}/{deliveryCompanyId}")
    public ResponseEntity deleteDeliveryCompany(@PathVariable Integer adminId, @PathVariable Integer deliveryCompanyId) {
        deliveryCompanyService.deleteDeliveryCompany(adminId, deliveryCompanyId);
        return ResponseEntity.status(200).body(new ApiResponse("Delivery Company deleted successfully"));
    }
}
