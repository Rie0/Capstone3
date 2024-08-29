package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.CommissionRequest;
import org.twspring.capstone3.Service.CommissionRequestService;

@RestController
@RequestMapping("/api/v1/commission")
@RequiredArgsConstructor
public class CommissionRequestController {
    private final CommissionRequestService commissionRequestService;
    @GetMapping("/get")
    public ResponseEntity getAllCommissionRequests() {
        return ResponseEntity.status(200).body(commissionRequestService.getAllCommissionRequests());
    }
    //extra
    @PostMapping("/{artEnthusiast_id}/request-commission/{shop_id}/{artist_id}")
    public ResponseEntity createCommissionRequest(@PathVariable Integer artEnthusiast_id,@PathVariable Integer shop_id,@PathVariable Integer artist_id, @Valid@RequestBody CommissionRequest commissionRequest) {

        commissionRequestService.createCommissionRequest(artEnthusiast_id,shop_id,artist_id,commissionRequest);
        return ResponseEntity.status(200).body(new ApiResponse( "Commission request added successfully"));
    }
    //extra
    @PutMapping("/update/{id}/{artEnthusiastId}")
    public ResponseEntity updateCommissionRequest(@PathVariable Integer id,@PathVariable Integer artEnthusiastId,@Valid @RequestBody CommissionRequest commissionRequest) {
        commissionRequestService.updateCommissionRequest(id,artEnthusiastId,commissionRequest);
        return ResponseEntity.status(200).body(new ApiResponse( "Commission request updated successfully"));
    }
    //extra
    @PutMapping("/cancel/{artEnthusiastId}/{id}")
    public ResponseEntity cancelCommissionRequest(@PathVariable Integer artEnthusiastId , @PathVariable Integer id) {
        commissionRequestService.cancelCommissionRequest(artEnthusiastId,id);
        return ResponseEntity.status(200).body(new ApiResponse( "Commission request deleted successfully"));
    }
    //extra
    @PutMapping("/{artistId}/change_status_of/{id}/to/{status}")
    public ResponseEntity changeCommissionRequestStatus(@PathVariable Integer artistId, @PathVariable Integer id,@PathVariable CommissionRequest.Status status){
        commissionRequestService.changeCommissionRequestStatus(artistId,id,status);
        return ResponseEntity.status(200).body(new ApiResponse( "Commission request changed successfully"));
    }

    @PutMapping("{artistId}/checkout-commission-order/{commissionId}")
    public ResponseEntity checkoutCommissionOrder(@PathVariable Integer artistId, @PathVariable Integer commissionId) {
        commissionRequestService.checkoutCommissionOrder(artistId, commissionId);
        return ResponseEntity.ok(new ApiResponse("Order checked out successfully"));
    }
}


