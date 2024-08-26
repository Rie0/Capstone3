package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Model.ExhibitionTicket;
import org.twspring.capstone3.Service.ExhibitionTicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exhibition-ticket")

public class ExhibitionTicketController {
    private final ExhibitionTicketService exhibitionTicketService;

    @GetMapping("/get")
    public ResponseEntity getAllExhibitionTickets() {
        return ResponseEntity.status(200).body(exhibitionTicketService.getAllExhibitionTickets());
    }
    @PostMapping("/add")
    public ResponseEntity issueExhibitionTicket(@PathVariable Integer artEnthusiast_id, @PathVariable Integer exhibition_id, @Valid @RequestBody ExhibitionTicket exhibitionTicket) {
        exhibitionTicketService.issueExhibitionTicket(artEnthusiast_id,exhibition_id ,exhibitionTicket);
        return ResponseEntity.status(200).body("Successfully added exhibition ticket");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateExhibitionTicket(@PathVariable Integer id,@Valid @RequestBody ExhibitionTicket exhibitionTicket) {
        exhibitionTicketService.updateExhibitionTicket(id,exhibitionTicket);
        return ResponseEntity.status(200).body("Successfully updated exhibition ticket");
    }
@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteExhibitionTicket(@PathVariable Integer id) {
        exhibitionTicketService.deleteExhibitionTicket(id);
        return ResponseEntity.status(200).body("Successfully deleted exhibition ticket");
    }
}
