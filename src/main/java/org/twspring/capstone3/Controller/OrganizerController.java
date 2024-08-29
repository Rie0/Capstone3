package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Organizer;
import org.twspring.capstone3.Service.OrganizerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;

    @GetMapping("/get")
    public ResponseEntity getAllOrganizers() {
        return ResponseEntity.status(200).body(organizerService.getAllOrganizers());
    }

    @PostMapping("/add")
    public ResponseEntity addOrganizer(@Valid @RequestBody Organizer organizer) {
        organizerService.addOrganizer(organizer);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrganizer(@PathVariable Integer id, @Valid @RequestBody Organizer organizer) {
        organizerService.updateOrganizer(id, organizer);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer updated successfully"));
    }

    //EP
    @PutMapping("/{adminId}/approve/{organizerId}")
    public ResponseEntity approveOrganizer(@PathVariable Integer adminId,
                                           @PathVariable Integer organizerId) {
        organizerService.approveOrganizer(adminId, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer approved successfully"));
    }

    //EP
    @PutMapping("/{adminId}/reject/{organizerId}")
    public ResponseEntity rejectOrganizer(@PathVariable Integer adminId,
                                          @PathVariable Integer organizerId) {
        organizerService.rejectOrganizer(adminId, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer rejected successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrganizer(@PathVariable Integer id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer deleted successfully"));
    }
}
