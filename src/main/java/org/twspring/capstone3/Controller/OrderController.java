package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.ArtOrder;
import org.twspring.capstone3.Service.OrderService;

@RestController
@RequestMapping("/api/v1/art-order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("{artEnthusiastId}/add-order-to-shop/{shopId}")
    public ResponseEntity addOrderToShop(@PathVariable Integer artEnthusiastId,
                                         @PathVariable Integer shopId) {
        orderService.addOrderToShop(artEnthusiastId, shopId);
        return ResponseEntity.ok(new ApiResponse("Successfully added order to shop"));
    }

    @PutMapping("{artEnthusiastId}/add-piece/{artPieceForSaleId}/to/{artOrderId}")
    public ResponseEntity addArtPieceToOrder(@PathVariable Integer artEnthusiastId, @PathVariable Integer artOrderId, @PathVariable Integer artPieceForSaleId) {
        orderService.addArtPieceToOrder(artEnthusiastId, artOrderId, artPieceForSaleId);
        return ResponseEntity.ok(new ApiResponse("Art piece added to order successfully"));
    }

    @PutMapping("{artEnthusiastId}/remove-piece/{artPieceForSaleId}/from/{artOrderId}")
    public ResponseEntity removeFromOrder(@PathVariable Integer artEnthusiastId, @PathVariable Integer artOrderId, @PathVariable Integer artPieceForSaleId) {
        orderService.removeFromOrder(artEnthusiastId, artOrderId, artPieceForSaleId);
        return ResponseEntity.ok(new ApiResponse("Art piece removed from order successfully"));
    }
    //extra
    @PutMapping("{artEnthusiastId}/checkout-art-piece-order/{artOrderId}")
    public ResponseEntity checkoutArtPieceOrder(@PathVariable Integer artEnthusiastId, @PathVariable Integer artOrderId) {
        orderService.checkoutArtPieceOrder(artEnthusiastId, artOrderId);
        return ResponseEntity.ok(new ApiResponse("Order checked out successfully"));
    }

    //extra
    @PutMapping("{artEnthusiastId}/pick-a-delivery-company/{deliveryCompanyId}/for-order/{orderId}")
    public ResponseEntity deliveryCompany(@PathVariable Integer artEnthusiastId,
                                          @PathVariable Integer orderId,
                                          @PathVariable Integer deliveryCompanyId) {
        orderService.pickDeliveryCompany(artEnthusiastId, orderId, deliveryCompanyId);
        return ResponseEntity.ok(new ApiResponse("Order picked delivery company successfully"));
    }
    //extra
    @PutMapping("/{deliveryCompanyId}/update-delivery-status/{artOrderId}")
    public ResponseEntity updateArtOrderAsDelivered(@PathVariable Integer deliveryCompanyId,
                                                    @PathVariable Integer artOrderId) {
        orderService.updateArtOrderAsDelivered(deliveryCompanyId,artOrderId);
        return ResponseEntity.ok(new ApiResponse("Order status updated to delivered successfully"));
    }
}
