package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Service.ArtistService;

@RestController
@RequestMapping("api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    @GetMapping("/get")
    public ResponseEntity getAllArtist(){
        return ResponseEntity .status(200).body(artistService.getAllArtists());
    }

    @PostMapping("/add")
    public  ResponseEntity addArtist(@Valid @RequestBody Artist artist){
        artistService.addArtist(artist);
        return  ResponseEntity .status(200).body(new ApiResponse( "Artist added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateArtist( @PathVariable Integer id, @Valid @RequestBody Artist artist){
        artistService.updateArtist(id, artist);
        return  ResponseEntity .status(200).body(new ApiResponse( "Artist updated successfully"));

    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteArtist( @PathVariable Integer id){
        artistService.deleteArtist(id);
        return  ResponseEntity .status(200).body(new ApiResponse( "Artist deleted successfully"));
    }
}
