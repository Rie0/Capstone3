package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.*;
import org.twspring.capstone3.Repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionRequestService {
    private final CommissionRequestRepository commissionRequestRepository;
    private final ArtistRepository artistRepository;
    private final ShopRepository shopRepository;
    private final ArtEnthusiastRepository artEnthusiastRepository;
    private final BillRepository billRepository;

    public List<CommissionRequest> getAllCommissionRequests() {
        return commissionRequestRepository.findAll();
    }


    //EP
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
            throw new ApiException("Price not accepted, minimal commission price is " + commissionRequest.getPrice());
        }

        Artist artist = artistRepository.findArtistById(artist_id);
        if (artEnthusiast == null || shop == null || artist == null) {
            throw new ApiException("ArtEnthusiast , Shop or artist are not found");
        }
        // Verify that the artist owns the shop related to the commission request
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
        currentCommissionRequest.setUpdatedAt(LocalDateTime.now());
        commissionRequestRepository.save(currentCommissionRequest);

    }
    //EP
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

    //EP
    public void changeCommissionRequestStatus(Integer artistId, Integer id, CommissionRequest.Status status) {
        CommissionRequest commissionRequest = commissionRequestRepository.findCommissionRequestById(id);
        if (commissionRequest == null) {
            throw new ApiException("CommissionRequest not found");
        }

        Artist artist1 = artistRepository.findArtistById(artistId);
        if (artist1 == null) {
            throw new ApiException("Artist not found");
        }

        Shop shop = commissionRequest.getShop();
        if (shop == null) {
            throw new ApiException("Commission request is not associated with any shop");
        }

        if (!shop.getArtist().getId().equals(artist1.getId())) {
            throw new ApiException("You do not have permission to update this commission request. Shop artist ID: "
                    + shop.getArtist().getId() + ", Your artist ID: " + artist1.getId());
        }

        switch (status) {
            case APPROVED:
            case COMPLETED:
                commissionRequest.setStatus(status);
                commissionRequestRepository.save(commissionRequest);
                break;
            default:
                throw new ApiException("Invalid status update");
        }
    }

    //EP
    public void checkoutCommissionOrder(Integer artistId, Integer commissionId){
        CommissionRequest commissionRequest = commissionRequestRepository.findCommissionRequestById(commissionId);
        if(commissionRequest==null){
            throw new ApiException("Commission Request with ID "+commissionId+" does not exist");
        }
        Artist artist = artistRepository.findArtistById(artistId);
        if(artist==null){
            throw new ApiException("Artist with ID "+artistId+" does not exist");
        }
        if(commissionRequest.getStatus()!= CommissionRequest.Status.COMPLETED){
            throw new ApiException("Commission status is not completed");
        }
        ArtEnthusiast artEnthusiast = commissionRequest.getArtEnthusiast();
        if(artEnthusiast==null){
            throw new ApiException("ArtEnthusiast does not exist");
        }
        //create Bill
        Bill bill = new Bill();
        bill.setProductsAmount(commissionRequest.getPrice());
        bill.setShippingFee(0);
        bill.setArtEnthusiast(artEnthusiast);
        billRepository.save(bill);
    }
}


