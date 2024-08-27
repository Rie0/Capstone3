package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Repository.ExhibitionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtEnthusiastService {
    private final ArtEnthusiastRepository artEnthusiastRepository;
    private final ExhibitionRepository exhibitionRepository;

    public List<ArtEnthusiast> getAllArtEnthusiasts() {
        return artEnthusiastRepository.findAll();
    }

    public void addArtEnthusiast(ArtEnthusiast artEnthusiast) {
        artEnthusiastRepository.save(artEnthusiast);
    }

    public void updateArtEnthusiast(Integer id, ArtEnthusiast artEnthusiast) {
        ArtEnthusiast existingArtEnthusiast = artEnthusiastRepository.findById(id).orElseThrow(() ->
                new ApiException("Art Enthusiast with id " + id + " not found")
        );
        existingArtEnthusiast.setUsername(artEnthusiast.getUsername());
        existingArtEnthusiast.setPassword(artEnthusiast.getPassword());
        existingArtEnthusiast.setEmail(artEnthusiast.getEmail());
//        existingArtEnthusiast.setUpdatedAt(artEnthusiast.getUpdatedAt());
        artEnthusiastRepository.save(existingArtEnthusiast);
    }

    public void deleteArtEnthusiast(Integer id) {
        ArtEnthusiast existingArtEnthusiast = artEnthusiastRepository.findById(id).orElseThrow(() ->
                new ApiException("Art Enthusiast with id " + id + " not found")
        );
        artEnthusiastRepository.delete(existingArtEnthusiast);
    }

    public void artEnthusiastBuyTicket(Integer artEnthusiast_id, Integer exhibition_id){
        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiast_id);
        Exhibition exhibition = exhibitionRepository.findExhibitionById(exhibition_id);
        if(artEnthusiast == null){
            throw new ApiException("Art enthusiast with id " + artEnthusiast_id + " not found");
        }if(exhibition == null){
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");
        }
        //if(exhibition.){}
        artEnthusiastRepository.save(artEnthusiast);
        exhibitionRepository.save(exhibition);
    }
}
