package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.DTO.ArtistProfileDTO;
import org.twspring.capstone3.Service.ArtistProfileService;

@RequiredArgsConstructor
@RequestMapping("api/v1/artist-profile")
@RestController
public class ArtistProfileController {
    private final ArtistProfileService artistProfileService;

    @GetMapping("/get")
    public ResponseEntity getArtistProfile() {
        return ResponseEntity.status(200).body(artistProfileService.getAllArtistProfiles());
    }
    @PostMapping("/add")
    public ResponseEntity createArtistProfile(@Valid @RequestBody ArtistProfileDTO artistProfileDTO){
        artistProfileService.createArtistProfile(artistProfileDTO);
        return ResponseEntity.status(201).body(new ApiResponse( "Artist Profile created successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateArtistProfile(@PathVariable Integer id,
                                              @Valid @RequestBody ArtistProfileDTO artistProfileDTO){
        artistProfileService.updateArtistProfile(artistProfileDTO);
        return ResponseEntity.status(201).body(new ApiResponse( "Artist Profile updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArtistProfile(@PathVariable Integer id){
        artistProfileService.deleteArtistProfile(id);
        return ResponseEntity.status(201).body(new ApiResponse( "Artist Profile deleted successfully"));
    }
}