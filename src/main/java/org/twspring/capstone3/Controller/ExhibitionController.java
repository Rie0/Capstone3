package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Service.ExhibitionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/exhibition")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @GetMapping("/get")
    public ResponseEntity getAllExhibitions() {
        return ResponseEntity.status(200).body(exhibitionService.getAllExhibitions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getExhibition(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(exhibitionService.getExhibitionById(id));
    }

    @PostMapping("/add")
    public ResponseEntity addExhibition(@Valid @RequestBody Exhibition exhibition) {
        exhibitionService.addExhibition(exhibition);
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
}
