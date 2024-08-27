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
        if (!shop.isCommissionOpen()){
            throw new ApiException("Commission IS CLOSED");
        }
        if (commissionRequest.getPrice()<=shop.getMinimalCommissionPrice()){
            throw new ApiException("Price not accepted");
        }

        Artist artist = artistRepository.findArtistById(artist_id);
        if (artEnthusiast == null || shop == null || artist == null) {
            throw new ApiException("ArtEnthusiast , Shop or artist are not found");
        }
        if (!shop.getArtist().getId().equals(artist_id)) {
            throw new ApiException("The artist does not own this shop");
        }
        commissionRequest.setArtEnthusiast(artEnthusiast);
        commissionRequest.setShop(shop);
        commissionRequest.setArtist(artist);
        commissionRequestRepository.save(commissionRequest);


    }
    public void updateCommissionRequest(Integer id,Integer artEnthusiastId,CommissionRequest commissionRequest) {
        CommissionRequest currentCommissionRequest = commissionRequestRepository.findCommissionRequestById(id);
        if (currentCommissionRequest == null) {
            throw new ApiException("CommissionRequest not found");
        }
        if (!currentCommissionRequest.getArtEnthusiast().getId().equals(artEnthusiastId)) {
            throw new ApiException("You do not have permission to update this commission request");
        }
        currentCommissionRequest.setDescription(commissionRequest.getDescription());
        currentCommissionRequest.setPrice(commissionRequest.getPrice());
//        currentCommissionRequest.setCreatedAt(currentCommissionRequest.getCreatedAt());
//        currentCommissionRequest.setUpdatedAt(currentCommissionRequest.getUpdatedAt());
        commissionRequestRepository.save(currentCommissionRequest);

    }
    public void cancelCommissionRequest(Integer artEnthusiastId,Integer id) {
        CommissionRequest currentCommissionRequest = commissionRequestRepository.findCommissionRequestById(id);
        if (currentCommissionRequest == null) {
            throw new ApiException("CommissionRequest not found");

        }
        if (!currentCommissionRequest.getArtEnthusiast().getId().equals(artEnthusiastId)) {
            throw new ApiException("You do not have permission to cancel this commission request");
        }
        currentCommissionRequest.setStatus(CommissionRequest.Status.CANCELED);
        commissionRequestRepository.save(currentCommissionRequest);
    }
//artist change the status of commession
    public void changeCommissionRequestStatus(Integer artistId, Integer id,CommissionRequest.Status status) {
        CommissionRequest commissionRequest = commissionRequestRepository.findCommissionRequestById(id);
        if(commissionRequest==null){

            throw  new ApiException("CommissionRequest not found");

        }

        if (!commissionRequest.getArtist().getId().equals(artistId)) {
            throw new ApiException("You do not have permission to update this commission request");
        }



        commissionRequest.setStatus(status);
        commissionRequestRepository.save(commissionRequest);
    }

}
