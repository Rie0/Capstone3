package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.OrderArt;
import org.twspring.capstone3.Service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity addOrder(@Valid @RequestBody OrderArt orderArt){
        orderService.addOrder(orderArt);
        return ResponseEntity.ok(new ApiResponse("Order added successfully"));
    }

    @PutMapping
    public ResponseEntity updateOrder(@RequestParam Integer id, @Valid @RequestBody OrderArt orderArt){
        orderService.updateOrder(id, orderArt);
        return ResponseEntity.ok(new ApiResponse("Order updated successfully"));
    }

    @DeleteMapping
    public ResponseEntity deleteOrder(@RequestParam Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok(new ApiResponse("Order deleted successfully"));
    }
}
