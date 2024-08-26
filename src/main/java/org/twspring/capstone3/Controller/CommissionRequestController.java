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
   @PostMapping("/add/{artEnthusiast_id}/{shop_id}/{artist_id}")
   public ResponseEntity addCommissionRequest(@PathVariable Integer artEnthusiast_id,@PathVariable Integer shop_id,@PathVariable Integer artist_id, @Valid@RequestBody CommissionRequest commissionRequest) {

       commissionRequestService.createCommissionRequest(artEnthusiast_id,shop_id,artist_id,commissionRequest);
       return ResponseEntity.status(200).body(new ApiResponse( "Commission request added successfully"));
   }
   @PutMapping("/update/{id}")
   public ResponseEntity updateCommissionRequest(@PathVariable Integer id,@Valid @RequestBody CommissionRequest commissionRequest) {
       commissionRequestService.updateCommissionRequest(id,commissionRequest);
       return ResponseEntity.status(200).body(new ApiResponse( "Commission request updated successfully"));
   }
   @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteCommissionRequest(@PathVariable Integer id) {
       commissionRequestService.deleteCommissionRequest(id);
       return ResponseEntity.status(200).body(new ApiResponse( "Commission request deleted successfully"));
   }
}
