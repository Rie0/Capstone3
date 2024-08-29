package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Bill;
import org.twspring.capstone3.Service.BillService;

@RestController
@RequestMapping("/api/v1/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping("/get")
    public ResponseEntity getAllBills(){
        return ResponseEntity.ok(billService.getAllBills());
    }
}

