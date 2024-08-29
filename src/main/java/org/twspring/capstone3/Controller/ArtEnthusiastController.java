package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Service.ArtEnthusiastService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/art-enthusiast")
public class ArtEnthusiastController {
    private final ArtEnthusiastService artEnthusiastService;

    @GetMapping("/get")
    public ResponseEntity getAllArtEnthusiasts() {
        return ResponseEntity.status(200).body(artEnthusiastService.getAllArtEnthusiasts());
    }

    @PostMapping("/add")
    public ResponseEntity addArtEnthusiast(@Valid @RequestBody ArtEnthusiast artEnthusiast) {
        artEnthusiastService.addArtEnthusiast(artEnthusiast);
        return ResponseEntity.status(200).body(new ApiResponse("Art Enthusiast added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArtEnthusiast(@PathVariable Integer id, @Valid @RequestBody ArtEnthusiast artEnthusiast) {
        artEnthusiastService.updateArtEnthusiast(id, artEnthusiast);
        return ResponseEntity.status(200).body(new ApiResponse("Art Enthusiast updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArtEnthusiast(@PathVariable Integer id) {
        artEnthusiastService.deleteArtEnthusiast(id);
        return ResponseEntity.status(200).body(new ApiResponse("Art Enthusiast deleted successfully"));
    }



}
