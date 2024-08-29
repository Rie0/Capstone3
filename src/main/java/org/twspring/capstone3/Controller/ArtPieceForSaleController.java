package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.ArtPieceForSale;
import org.twspring.capstone3.Service.ArtPieceForSaleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/art-piece-for-sale")


public class ArtPieceForSaleController {
    private final ArtPieceForSaleService artPieceForSaleService;

    @GetMapping("/get")
    public ResponseEntity getAllArtPieceForSale(){
        return ResponseEntity.status(200).body(artPieceForSaleService.getAllArtPieceForSale());
    }

//   + EndPoint X
    @PostMapping("/add/{shopId}")
    public ResponseEntity addArtPieceForSale(@PathVariable Integer shopId,
                                             @Valid @RequestBody ArtPieceForSale artPieceForSale){
        artPieceForSaleService.addArtPieceForSale(artPieceForSale, shopId);
        return ResponseEntity.status(200).body("Successfully added Art piece for sale");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArtPieceForSale(@PathVariable Integer id,@Valid @RequestBody ArtPieceForSale artPieceForSale ){
        artPieceForSaleService.updateArtPieceForSale(artPieceForSale , id);
        return ResponseEntity.status(200).body("Successfully updated Art piece for sale");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArtPieceForSale(@PathVariable Integer id){
        artPieceForSaleService.deleteArtPieceForSale(id);
        return ResponseEntity.status(200).body("Successfully deleted Art piece for sale");
    }

    //    + EndPoint X
    @PostMapping("/add-multiple-art-pieces/{shopId}")
    public ResponseEntity addMultipleArtPieces(@PathVariable Integer shopId, @RequestBody List<Integer> artPieceIds){
        artPieceForSaleService.addMultipleArtPieces(shopId, artPieceIds);
        return ResponseEntity.ok(new ApiResponse("Art pieces added successfully"));
    }
}
