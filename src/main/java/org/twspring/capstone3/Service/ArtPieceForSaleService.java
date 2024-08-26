package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtPieceForSale;
import org.twspring.capstone3.Repository.ArtPieceForSaleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtPieceForSaleService {
    private final ArtPieceForSaleRepository artPieceForSaleRepository;

    public List<ArtPieceForSale> getAllArtPieceForSale(){

        return artPieceForSaleRepository.findAll();
    }

    public void addArtPieceForSale(ArtPieceForSale artPieceForSale){

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




}
