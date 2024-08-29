package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.ExhibitionTicket;
import org.twspring.capstone3.Service.ExhibitionTicketService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exhibition-ticket")

public class ExhibitionTicketController {
    private final ExhibitionTicketService exhibitionTicketService;

    @GetMapping("/get")
    public ResponseEntity getAllExhibitionTickets() {
        return ResponseEntity.status(200).body(exhibitionTicketService.getAllExhibitionTickets());
    }
    //extra
    @PostMapping("/add/artEnthusiast/{artEnthusiast_id}/to exhibition/{exhibition_id}")
    public ResponseEntity issueExhibitionTicket(@PathVariable Integer artEnthusiast_id, @PathVariable Integer exhibition_id) {
        exhibitionTicketService.issueExhibitionTicket(artEnthusiast_id,exhibition_id);
        return ResponseEntity.status(200).body("Successfully bought exhibition ticket");
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
    //extra
    @PutMapping("/cancel-ticket/ticket/{ticketId}/artEnthusiast/{enthusiastId}")
    public ResponseEntity cancelTicket(@PathVariable Integer ticketId, @PathVariable Integer enthusiastId) {
        exhibitionTicketService.cancelTicket(ticketId, enthusiastId);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket cancelled successfully"));
    }
    //extra
    @GetMapping("/getTicketsByArtEnthusiast/{artEnthusiastId}")
    public List<ExhibitionTicket> getTicketsByArtEnthusiast(@PathVariable Integer artEnthusiastId) {
        return exhibitionTicketService.getTicketsByArtEnthusiast(artEnthusiastId);
    }

    //extra
    @GetMapping("/getTicketsByAmount")
    public List<ExhibitionTicket> getTicketsByAmount(@PathVariable double amount) {
        return exhibitionTicketService.getTicketsByAmount(amount);
    }
    //extra
    @GetMapping("/getTicketsByExhibition/{exhibitionId}")
    public List<ExhibitionTicket> getTicketsByExhibition(@PathVariable Integer exhibitionId) {
        return exhibitionTicketService.getTicketsByExhibition(exhibitionId);
    }






}
