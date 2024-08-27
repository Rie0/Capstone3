package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.CommissionRequest;
import org.twspring.capstone3.Model.Shop;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Repository.ArtistRepository;
import org.twspring.capstone3.Repository.CommissionRequestRepository;
import org.twspring.capstone3.Repository.ShopRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionRequestService {
    private final CommissionRequestRepository commissionRequestRepository;
    private final ArtistRepository artistRepository;
    private final ShopRepository shopRepository;
    private final ArtEnthusiastRepository artEnthusiastRepository;

    public List<CommissionRequest> getAllCommissionRequests() {
        return commissionRequestRepository.findAll();
    }
    public void createCommissionRequest(Integer artEnthusiast_id,Integer shop_id,Integer artist_id,CommissionRequest commissionRequest) {
        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiast_id);
        Shop shop = shopRepository.findShopById(shop_id);
        if(shop == null ){
            throw new ApiException("SHOP NOT FOUND");
        }

        Artist artist = artistRepository.findArtistById(artist_id);
        if (artEnthusiast == null || shop == null || artist == null) {
            throw new RuntimeException("ArtEnthusiast , Shop and artist are not found");
        }
        commissionRequest.setArtEnthusiast(artEnthusiast);
        commissionRequest.setShop(shop);
        commissionRequest.setArtist(artist);
        commissionRequestRepository.save(commissionRequest);


    }
    public void updateCommissionRequest(Integer id,CommissionRequest commissionRequest) {
        CommissionRequest currentCommissionRequest = commissionRequestRepository.findCommissionRequestById(id);
        if (currentCommissionRequest == null) {
            throw new RuntimeException("CommissionRequest not found");
        }
        currentCommissionRequest.setDescription(commissionRequest.getDescription());
        currentCommissionRequest.setPrice(commissionRequest.getPrice());
//        currentCommissionRequest.setCreatedAt(currentCommissionRequest.getCreatedAt());
//        currentCommissionRequest.setUpdatedAt(currentCommissionRequest.getUpdatedAt());
        commissionRequestRepository.save(currentCommissionRequest);

    }
    public void deleteCommissionRequest(Integer id) {
        CommissionRequest currentCommissionRequest = commissionRequestRepository.findCommissionRequestById(id);
        if (currentCommissionRequest == null) {
            throw new RuntimeException("CommissionRequest not found");

        }
        commissionRequestRepository.delete(currentCommissionRequest);
    }

}
