package org.twspring.capstone3.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.twspring.capstone3.Model.Shop;

@Service
@RequiredArgsConstructor
public class ShopService {
//    private final ShopRepository shopRepository;
//
//    public List<Shop> getAllShop(){
//        return shopRepository.findAll();
//    }
//
//    public void addShop(Shop shop){
//        shopRepository.save(shop);
//    }
//
//    public void updateShop(Integer id, Shop updateShop){
//        Shop shop = shopRepository.findShopById(id)
//                .orElseThrow(() -> new ApiException("SHOP NOT FOUND"));
//
//        shop.setCommissionOpen(updateShop.isCommissionOpen());
//        shop.setMinimalCommissionPrice(updateShop.getMinimalCommissionPrice());
//
//        shopRepository.save(shop);
//    }
//
//
//    public void deleteShop(Integer id){
//        Shop shop = shopRepository.findShopById(id)
//                .orElseThrow(() -> new ApiException("SHOP NOT FOUND"));
//
//        shopRepository.delete(shop);
//    }
}
