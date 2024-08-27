package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Service.ExhibitionService;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/exhibition")
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

    @GetMapping("/rent/{artist_id}/{start_date}/{end_date}")
    public ResponseEntity rentExhibitionForArtists(@PathVariable Integer artist_id, @PathVariable LocalDate start_date, @PathVariable LocalDate end_date) {
        exhibitionService.rentExhibitionForArtists(artist_id,start_date,end_date);
        return ResponseEntity.status(200).body(new ApiResponse("Artist rent exhibition successfully"));
    }


}
