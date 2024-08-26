package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Service.ArtWorkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/artWork")
public class ArtWorkController {
    private final ArtWorkService artWorkService;

    @GetMapping("/get")
    public ResponseEntity getAllArtWork() {
        return ResponseEntity.status(200).body(artWorkService.getAllArtWork());
    }

    @PostMapping("/add/{artist_id}")
    public ResponseEntity addArtWorkToArtist(@PathVariable Integer artist_id, @Valid @RequestBody ArtWork artWork) {
        artWorkService.addArtWorkToArtist(artist_id, artWork);
        return ResponseEntity.status(200).body(new ApiResponse("Art work added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArtWork(@PathVariable Integer id, @Valid @RequestBody ArtWork artWork) {
        artWorkService.updateArtWork(id, artWork);
        return ResponseEntity.status(200).body(new ApiResponse("Art work updated successfully"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArtWorkFromArtist(@PathVariable Integer id) {
        artWorkService.deleteArtWork(id);
        return ResponseEntity.status(200).body(new ApiResponse("Art work deleted successfully"));
    }
}