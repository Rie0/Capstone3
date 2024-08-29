package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Model.ArtOrder;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Model.ExhibitionTicket;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Repository.ExhibitionRepository;
import org.twspring.capstone3.Repository.ExhibitionTicketRepository;
import org.twspring.capstone3.Repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtEnthusiastService {
    private final ArtEnthusiastRepository artEnthusiastRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionTicketRepository exhibitionTicketRepository;
    private final OrderRepository orderRepository;
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
        existingArtEnthusiast.setUpdatedAt(LocalDateTime.now());
        artEnthusiastRepository.save(existingArtEnthusiast);
    }

    public void deleteArtEnthusiast(Integer id) {
        ArtEnthusiast existingArtEnthusiast = artEnthusiastRepository.findById(id).orElseThrow(() ->
                new ApiException("Art Enthusiast with id " + id + " not found")
        );
        artEnthusiastRepository.delete(existingArtEnthusiast);
    }

}

