package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Admin;
import org.twspring.capstone3.Service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity getAllAdmins() {
        return ResponseEntity.status(200).body(adminService.getAllAdmins());
    }

    @PostMapping("/add")
    public ResponseEntity addAdmin(@Valid @RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return ResponseEntity.status(200).body(new ApiResponse("Admin added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAdmin(@PathVariable Integer id, @Valid @RequestBody Admin admin) {
        adminService.updateAdmin(id, admin);
        return ResponseEntity.status(200).body(new ApiResponse("Admin updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.status(200).body(new ApiResponse("Admin deleted successfully"));
    }
}
