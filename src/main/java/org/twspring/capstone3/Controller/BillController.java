package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Bill;
import org.twspring.capstone3.Service.BillService;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    public ResponseEntity getAllBills(){
        return ResponseEntity.ok(billService.getAllBills());
    }

    @PostMapping
    public ResponseEntity addBill(@Valid @RequestBody Bill bill){
        billService.addBill(bill);
        return ResponseEntity.ok(new ApiResponse("Bill added successfully"));
    }

    @PutMapping
    public ResponseEntity updateBill(@RequestParam Integer id, @Valid @RequestBody Bill bill){
        billService.updateBill(id, bill);
        return ResponseEntity.ok(new ApiResponse("Bill updated successfully"));
    }

    @DeleteMapping
    public ResponseEntity deleteBill(@RequestParam Integer id){
        billService.deleteBill(id);
        return ResponseEntity.ok(new ApiResponse("Bill deleted successfully"));
    }
}

