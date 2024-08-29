package org.twspring.capstone3.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.*;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Repository.ArtPieceForSaleRepository;
import org.twspring.capstone3.Repository.DeliveryCompanyRepository;
import org.twspring.capstone3.Repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ArtEnthusiastRepository artEnthusiastRepository;
    private final ArtPieceForSaleRepository artPieceForSaleRepository;
    private final DeliveryCompanyRepository deliveryCompanyRepository;

    public List<ArtOrder> getAllOrders() { //for shop/for user
        return orderRepository.findAll();
    }

    //EXTRA56
    public void addArtPieceToOrder(Integer artEnthusiastId, Integer artOrderId, Integer artPieceForSaleId){
        ArtOrder artOrder = orderRepository.findOrderById(artOrderId);
        if(artOrder==null){
            throw new ApiException("Art order with ID "+artOrderId+" does not exist");
        }

        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiastId);
        if(artEnthusiast==null){
            throw new ApiException("Art enthusiast with ID "+artOrderId+" does not exist");
        }

        ArtPieceForSale artPieceForSale = artPieceForSaleRepository.findArtPieceForSaleById(artPieceForSaleId);
        if(artPieceForSale==null){
            throw new ApiException("Art piece for Sale with ID "+artOrderId+" does not exist");
        }
        if (!artPieceForSale.isSold()){
            throw new ApiException("Art piece already sold");
        }
        if(artOrder.getStatus()!=ArtOrder.Status.ACTIVE){
            throw new ApiException("Art order status is not active, create a new order");
        }
        artOrder.setTotalPrice(artOrder.getTotalPrice()+artPieceForSale.getPrice());
        artOrder.getArtPieceForSale().add(artPieceForSale);
        orderRepository.save(artOrder);
    }

    //EXTRA
    public void removeFromOrder(Integer artEnthusiastId, Integer artOrderId, Integer artPieceForSaleId){
        ArtOrder artOrder = orderRepository.findOrderById(artOrderId);
        if(artOrder==null){
            throw new ApiException("Art order with ID "+artOrderId+" does not exist");
        }

        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiastId);
        if(artEnthusiast==null){
            throw new ApiException("Art enthusiast with ID "+artOrderId+" does not exist");
        }
        ArtPieceForSale artPieceForSale = artPieceForSaleRepository.findArtPieceForSaleById(artPieceForSaleId);
        if(artPieceForSale==null){
            throw new ApiException("Art piece for Sale with ID "+artOrderId+" does not exist");
        }
        if(artOrder.getStatus()!=ArtOrder.Status.ACTIVE){
            throw new ApiException("Art order status is not active, create a new order");
        }
        artOrder.setTotalPrice(artOrder.getTotalPrice()-artPieceForSale.getPrice());
        artOrder.getArtPieceForSale().remove(artPieceForSale);
        orderRepository.save(artOrder);
    }

    public void pickDeliveryCompany(Integer artEnthusiastId, Integer artOrderId, Integer deliveryCompanyId){
        ArtOrder artOrder = orderRepository.findOrderById(artOrderId);
        if(artOrder==null){
            throw new ApiException("Art order with ID "+artOrderId+" does not exist");
        }
        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiastId);
        if(artEnthusiast==null){
            throw new ApiException("Art enthusiast with ID "+artEnthusiastId+" does not exist");
        }
        DeliveryCompany deliveryCompany= deliveryCompanyRepository.getDeliveryCompaniesById(deliveryCompanyId);
        if(deliveryCompany==null){
            throw new ApiException("Delivery company with ID "+deliveryCompanyId+" does not exist");
        }
        artOrder.setDeliveryCompany(deliveryCompany);
        orderRepository.save(artOrder);
    }

    public void checkoutOrder(Integer artEnthusiastId, Integer artOrderId){
        ArtOrder artOrder = orderRepository.findOrderById(artOrderId);
        if(artOrder==null){
            throw new ApiException("Art order with ID "+artOrderId+" does not exist");
        }

        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiastId);
        if(artEnthusiast==null){
            throw new ApiException("Art enthusiast with ID "+artOrderId+" does not exist");
        }

        if(artOrder.getStatus()!=ArtOrder.Status.ACTIVE){
            throw new ApiException("Art order status is not active, create a new order");
        }
        if(artOrder.getDeliveryCompany()==null){
            throw new ApiException("You must pick a delivery company first");
        }
        //change status and create new order
        artOrder.setStatus(ArtOrder.Status.SHIPPED);
        ArtOrder newArtOrder=new ArtOrder();
        artEnthusiast.getArtOrders().add(newArtOrder);
        for(ArtPieceForSale artPieceForSale : artOrder.getArtPieceForSale()){
            artPieceForSale.setSold(true);
            artPieceForSaleRepository.save(artPieceForSale);
        }
        orderRepository.save(artOrder);
        artEnthusiastRepository.save(artEnthusiast);
        //create Bill
        Bill bill = new Bill();
        bill.setProductsAmount(artOrder.getTotalPrice());
        bill.setShippingFee(artOrder.getDeliveryCompany().getDeliveryFee());
        artEnthusiast.getBills().add(bill);
        artOrder.setBill(bill);
    }


    public void updateArtOrderAsDelivered(Integer deliveryCompanyId, Integer artOrderId){
        ArtOrder artOrder = orderRepository.findOrderById(artOrderId);
        if(artOrder==null){
            throw new ApiException("Art order with ID "+artOrderId+" does not exist");
        }

        DeliveryCompany deliveryCompany= deliveryCompanyRepository.getDeliveryCompaniesById(deliveryCompanyId);
        if(deliveryCompany==null){
            throw new ApiException("Delivery company with ID "+deliveryCompanyId+" does not exist");
        }
        if(artOrder.getDeliveryCompany().getId()!=deliveryCompany.getId()){
            throw new ApiException("This order is not owned by this delivery company");
        }
        if(artOrder.getStatus()!=ArtOrder.Status.SHIPPED){
            throw new ApiException("Art order is not shipped");
        }
        artOrder.setStatus(ArtOrder.Status.DELIVERED);
        orderRepository.save(artOrder);
    }
}