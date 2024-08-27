package org.twspring.capstone3.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Service.ShopService;

@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping()
    public ResponseEntity getAllShop(){
        return ResponseEntity.ok(shopService.getAllShop());
    }

//    + EndPoint
    @PostMapping
    public ResponseEntity addShop(@RequestParam Integer id, @Valid @RequestBody Shop shop){
        shopService.addShop(shop, id);
        return ResponseEntity.ok(new ApiResponse("Shop added successfully"));
    }

    @PutMapping
    public ResponseEntity updateShop(@RequestParam Integer id, @Valid @RequestBody Shop shop){
        shopService.updateShop(id, shop);
        return ResponseEntity.ok(new ApiResponse("Shop updated successfully"));
    }

    @DeleteMapping
    public ResponseEntity deleteShop(@RequestParam Integer id){
        shopService.deleteShop(id);
        return ResponseEntity.ok(new ApiResponse("Shop deleted successfully"));
    }
//    + EndPonit
    @PutMapping("/switch-commission")
    public ResponseEntity switchIsCommissionOpen(@RequestParam Integer id){
        shopService.switchIsCommissionOpen(id);
        return ResponseEntity.ok(new ApiResponse("Switch isCommissionOpen successfully"));
    }

//    + EndPoint
    @PutMapping("/edit-minimal-commission/{id}/{price}")
    public ResponseEntity minimalCommissionPrice(@PathVariable Integer id, @PathVariable double price){
        shopService.minimalCommissionPrice(id, price);
        return ResponseEntity.ok(new ApiResponse("Minimal commission price updated successfully"));
    }
}
