package org.twspring.capstone3.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.DTO.ArtistShopDTO;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/get")
    public ResponseEntity getAllShop(){
        return ResponseEntity.ok(shopService.getAllShop());
    }

//    + EndPoint X
    @PostMapping("/add")
    public ResponseEntity addShop(@Valid @RequestBody ArtistShopDTO artistShopDTO){
        shopService.addShop(artistShopDTO);
        return ResponseEntity.ok(new ApiResponse("Shop added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateShop(@PathVariable Integer id, @Valid @RequestBody Shop shop){
        shopService.updateShop(id, shop);
        return ResponseEntity.ok(new ApiResponse("Shop updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteShop(@PathVariable Integer id){
        shopService.deleteShop(id);
        return ResponseEntity.ok(new ApiResponse("Shop deleted successfully"));
    }
//    + EndPonit X
    @PutMapping("/switch-commission/{id}")
    public ResponseEntity switchIsCommissionOpen(@PathVariable Integer id){
        shopService.switchIsCommissionOpen(id);
        return ResponseEntity.ok(new ApiResponse("Switch isCommissionOpen successfully"));
    }

//    + EndPoint X
    @PutMapping("/edit-minimal-commission/{id}/{price}")
    public ResponseEntity minimalCommissionPrice(@PathVariable Integer id, @PathVariable double price){
        shopService.minimalCommissionPrice(id, price);
        return ResponseEntity.ok(new ApiResponse("Minimal commission price updated successfully"));
    }

//    + EndPoint X
    @GetMapping("/shops-with-open-commissions")
    public ResponseEntity getShopsWithOpenCommissions(){
        return ResponseEntity.ok(shopService.getShopsWithOpenCommissions());
    }

//    + EndPoint X
    @PutMapping("/apply-discount/{shopId}/{discount}")
    public ResponseEntity applyDiscountToAllArtPieces(@PathVariable Integer shopId, @PathVariable double discount){
        shopService.applyDiscountToAllArtPieces(shopId, discount);
        return ResponseEntity.ok(new ApiResponse("Apply discount successfully"));
    }

//    + EndPoint X
    @PutMapping("/change-ownership/{artPieceId}/{newShopId}")
    public ResponseEntity changeArtPieceOwnership(@PathVariable Integer artPieceId, @PathVariable Integer newShopId){
        shopService.changeArtPieceOwnership(artPieceId, newShopId);
        return ResponseEntity.ok(new ApiResponse("Change art piece ownership successfully"));
    }

//    + EndPoint X
    @GetMapping("/total-sold/{shopId}")
    public ResponseEntity totalSold(@PathVariable Integer shopId){
        return ResponseEntity.ok(shopService.totalSold(shopId));
    }

//    + EndPoint X
    @GetMapping("/total-unSold/{shopId}")
    public ResponseEntity totalUnSold(@PathVariable Integer shopId){
        return ResponseEntity.ok(shopService.totalUnSold(shopId));
    }

}
