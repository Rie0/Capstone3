package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Service.ExhibitionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exhibition")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @GetMapping("/get")
    public ResponseEntity getAllExhibitions() {
        return ResponseEntity.status(200).body(exhibitionService.getAllExhibitions());
    }

    @PostMapping("/add/{organizerId}")
    public ResponseEntity addExhibition(@PathVariable Integer organizerId,
                                        @Valid @RequestBody Exhibition exhibition) {
        exhibitionService.addExhibitionToOrganizer(organizerId,exhibition);
        return ResponseEntity.status(200).body(new ApiResponse("Exhibition added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateExhibition(@PathVariable Integer id, @Valid @RequestBody Exhibition exhibition) {
        exhibitionService.updateExhibition(id, exhibition);
        return ResponseEntity.status(200).body(new ApiResponse("Exhibition updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteExhibition(@PathVariable Integer id) {
        exhibitionService.deleteExhibition(id);
        return ResponseEntity.status(200).body(new ApiResponse("Exhibition deleted successfully"));
    }
    //extra
    @PutMapping("/rent/{exhibition_id}/to/{artist_id}/from/{startDate}/to/{endDate}/price=/{ticketPrice}")
    public ResponseEntity rentExhibitionForArtist(@PathVariable Integer exhibition_id, @PathVariable Integer artist_id, @PathVariable  LocalDate startDate, @PathVariable LocalDate endDate,@PathVariable double ticketPrice) {
        exhibitionService.rentExhibitionForArtist(exhibition_id,artist_id,startDate,endDate,ticketPrice);
        return ResponseEntity.status(200).body(new ApiResponse("Artist rent exhibition successfully"));
    }
    //extra
    @PutMapping("/end-rent/exhibition/{exhibition_id}/organizer/{organizer_id}")
    public ResponseEntity endRentExhibition(@PathVariable Integer exhibition_id,@PathVariable Integer organizer_id) {
        exhibitionService.endRentExhibition(exhibition_id,organizer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Exhibition ended successfully"));
    }
    //extra
    @PutMapping("/extend/exhibitiion/{exhibition_id}/organiizer/{organizer_id}/{newEndDate}")
    public ResponseEntity extendExhibition(@PathVariable Integer exhibition_id,@PathVariable Integer organizer_id,@PathVariable LocalDate newEndDate){
        exhibitionService.extendExhibition(exhibition_id,organizer_id,newEndDate);
        return ResponseEntity.status(200).body(new ApiResponse("Exhibition extended successfully"));
    }
    //extra
    @PutMapping("/cancel/exhibittion/{exhibitionId}/artist/{artistId}")
    public ResponseEntity cancelExhibition(@PathVariable Integer exhibitionId, @PathVariable Integer artistId) {
        exhibitionService.cancelExhibitionForArtist(exhibitionId, artistId);
        return ResponseEntity.status(200).body(new ApiResponse("Exhibition canceled successfully"));
    }

    //extra
    @GetMapping("/available-for-rent")
    public List<Exhibition> getExhibitionsByAvailability(@RequestParam Boolean isAvailableForRent) {
        return exhibitionService.getExhibitionsByAvailability(isAvailableForRent);
    }




}
