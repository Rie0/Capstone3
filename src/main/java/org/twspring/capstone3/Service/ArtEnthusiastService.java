package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Api.ApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtEnthusiastService {
    private final ArtEnthusiastRepository artEnthusiastRepository;

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
}
