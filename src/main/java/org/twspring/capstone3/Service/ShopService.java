package org.twspring.capstone3.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<Shop> getAllShop(){
        return shopRepository.findAll();
    }

//    + 1
    public void addShop(Shop shop){
        shopRepository.save(shop);
    }

    public void assignArtistToShop(Integer artistId, Integer shopId) {
        Artist artist = artistRepository.findArtistById(artistId);
        if(artist == null) {
            throw new ApiException("Artist not found");
        }

        Shop shop = shopRepository.findShopById(shopId);
        if(shop == null) {
            throw new ApiException("Shop not found");
        }

        artist.setShop(shop);
        artistRepository.save(artist);
    }
    public void updateShop(Integer id, Shop updateShop){
        Shop shop = shopRepository.findShopById(id);
        if(shop == null){
            throw new ApiException("SHOP NOT FOUND");
        }

        shop.setCommissionOpen(updateShop.isCommissionOpen());
        shop.setMinimalCommissionPrice(updateShop.getMinimalCommissionPrice());

        shopRepository.save(shop);
    }


    public void deleteShop(Integer id){
        Shop shop = shopRepository.findShopById(id);
        if(shop == null){
            throw new ApiException("SHOP NOT FOUND");
        }

        shopRepository.delete(shop);
    }
}
