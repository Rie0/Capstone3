package org.twspring.capstone3.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Repository.ArtistRepository;
import org.twspring.capstone3.Repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ArtistRepository artistRepository;

    public List<Shop> getAllShop(){
        return shopRepository.findAll();
    }

    public void addShop(Shop shop, Integer artistId){
        Artist artist = artistRepository.findArtistById(artistId);
        if(artist == null){
            throw new ApiException("ARTIST DOES NOT EXIST");
        }
        shop.setArtist(artist);
        shopRepository.save(shop);
    }

    public void updateShop(Integer id, Shop updateShop){
        Shop shop = shopRepository.findShopById(id);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        shop.setCommissionOpen(updateShop.isCommissionOpen());
        shop.setMinimalCommissionPrice(updateShop.getMinimalCommissionPrice());

        shopRepository.save(shop);
    }


    public void deleteShop(Integer id){
        Shop shop = shopRepository.findShopById(id);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        shopRepository.delete(shop);
    }

    public void switchIsCommissionOpen(Integer id){
        Shop shop = shopRepository.findShopById(id);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        if(shop.isCommissionOpen()){
            shop.setCommissionOpen(false);
        }else {
            shop.setCommissionOpen(true);
        }
        shopRepository.save(shop);
    }

    public void minimalCommissionPrice(Integer id, double price){
        Shop shop = shopRepository.findShopById(id);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }
        shop.setMinimalCommissionPrice(price);
        shopRepository.save(shop);
    }
}
