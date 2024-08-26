package org.twspring.capstone3.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.OrderArt;
import org.twspring.capstone3.Repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderArt> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(OrderArt orderArt){
        orderRepository.save(orderArt);
    }

    public void updateOrder(Integer id, OrderArt updateOrderArt){
        OrderArt orderArt = orderRepository.findOrderById(id)
                .orElseThrow(() -> new ApiException("ORDER NOT FOUND"));

        orderRepository.save(orderArt);
    }

    public void deleteOrder(Integer id){
        OrderArt orderArt = orderRepository.findOrderById(id)
                .orElseThrow(() -> new ApiException("ORDER NOT FOUND"));
        orderRepository.delete(orderArt);
    }
}
