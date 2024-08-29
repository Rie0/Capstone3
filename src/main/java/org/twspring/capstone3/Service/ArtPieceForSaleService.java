package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtPieceForSale;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Repository.ArtPieceForSaleRepository;
import org.twspring.capstone3.Repository.ShopRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtPieceForSaleService {
    private final ArtPieceForSaleRepository artPieceForSaleRepository;
    private final ShopRepository shopRepository;

    public List<ArtPieceForSale> getAllArtPieceForSale(){
        return artPieceForSaleRepository.findAll();
    }

    //EP
    public void addArtPieceForSale(ArtPieceForSale artPieceForSale, Integer shopId){
        Shop shop = shopRepository.findShopById(shopId);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        artPieceForSale.setShop(shop);
        artPieceForSaleRepository.save(artPieceForSale);
    }

    public void updateArtPieceForSale(ArtPieceForSale artPieceForSale , Integer id){
        ArtPieceForSale a = artPieceForSaleRepository.findArtPieceForSaleById(id);
        if(a==null){
            throw new ApiException("Art Piece For Sale not found");
        }
        artPieceForSale.setName(artPieceForSale.getName());
        artPieceForSale.setAbout(artPieceForSale.getAbout());
        artPieceForSale.setPrice(artPieceForSale.getPrice());
        artPieceForSale.setSold(artPieceForSale.isSold());
        artPieceForSaleRepository.save(artPieceForSale);
    }

    public void deleteArtPieceForSale(Integer id){
        ArtPieceForSale a= artPieceForSaleRepository.findArtPieceForSaleById(id);
        if(a==null){
            throw new ApiException("Art Piece For Sale not found");
        }
        artPieceForSaleRepository.delete(a);
    }


    //EP
    public void addMultipleArtPieces(Integer shopId, List<Integer> artPieceIds){
        Shop shop = shopRepository.findShopById(shopId);
        if(shop == null){
            throw new ApiException("SHOP DOES NOT EXIST");
        }

        List<ArtPieceForSale> artPieces = artPieceForSaleRepository.findAllByIds(artPieceIds);
        for(ArtPieceForSale artPiece : artPieces){
            if(artPiece == null){
                throw new ApiException("ART PIECE DOES NOT EXIST: " + artPiece.getId());
            }
            artPiece.setShop(shop);
        }
        artPieceForSaleRepository.saveAll(artPieces);
    }
}
