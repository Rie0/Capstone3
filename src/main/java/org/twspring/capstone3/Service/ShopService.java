package org.twspring.capstone3.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.DTO.ArtistShopDTO;
import org.twspring.capstone3.Model.ArtPieceForSale;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Repository.ArtPieceForSaleRepository;
import org.twspring.capstone3.Repository.ArtistRepository;
import org.twspring.capstone3.Repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ArtistRepository artistRepository;
    private final ArtPieceForSaleRepository artPieceForSaleRepository;

    public List<Shop> getAllShop(){
        return shopRepository.findAll();
    }

    public void addShop(ArtistShopDTO artistShopDTO){
        Artist artist = artistRepository.findArtistById(artistShopDTO.getArtist_id());
        if(artist == null){
            throw new ApiException("ARTIST DOES NOT EXIST");
        }

        Shop shop = new Shop(
                null,
                artistShopDTO.isCommissionOpen(),
                artistShopDTO.getMinimalCommissionPrice(),
                artist
        );

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


    public List<Shop> getShopsWithOpenCommissions(){
        return shopRepository.findShopByIsCommissionOpenTrue();
    }

    public void applyDiscountToAllArtPieces(Integer shopId, double discountPercentage){
        Shop shop = shopRepository.findShopById(shopId);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        if(discountPercentage <= 0 || discountPercentage > 100){
            throw new ApiException("INVALID DISCOUNT PERCENTAGE");
        }

        List<ArtPieceForSale> artPieces = artPieceForSaleRepository.findArtPieceForSaleByShopId(shopId);
        for(ArtPieceForSale artPiece : artPieces){
            double newPrice = artPiece.getPrice() * (1 - discountPercentage / 100);
            artPiece.setPrice(newPrice);
        }
        artPieceForSaleRepository.saveAll(artPieces);
    }

    public Integer totalSold(Integer shopId){
        Integer total = 0;
        Shop shop = shopRepository.findShopById(shopId);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        for(ArtPieceForSale isSold : artPieceForSaleRepository.findArtPieceForSaleByShopId(shopId)){
            if(isSold.isSold()){
                total++;
            }
        }

        return total;
    }


    public Integer totalUnSold(Integer shopId){
        Integer total = 0;
        Shop shop = shopRepository.findShopById(shopId);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        for(ArtPieceForSale isSold : artPieceForSaleRepository.findArtPieceForSaleByShopId(shopId)){
            if(!isSold.isSold()){
                total++;
            }
        }
        return total;
    }


    public void changeArtPieceOwnership(Integer artPieceId, Integer newShopId){
        ArtPieceForSale artPiece = artPieceForSaleRepository.findArtPieceForSaleById(artPieceId);
        if(artPiece == null){
            throw new ApiException("ART PIECE DOES NOT EXIST");
        }

        Shop shop = shopRepository.findShopById(newShopId);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        artPiece.setShop(shop);
        artPieceForSaleRepository.save(artPiece);
    }
}
