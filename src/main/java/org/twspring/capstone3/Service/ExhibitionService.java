package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Repository.ArtistRepository;
import org.twspring.capstone3.Repository.ExhibitionRepository;
import org.twspring.capstone3.Api.ApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ArtistRepository artistRepository;

    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    public Exhibition getExhibitionById(Integer id) {
        return exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition not found")
        );
    }

    public void addExhibition(Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
    }

    public void updateExhibition(Integer id, Exhibition exhibition) {
        Exhibition existingExhibition = exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition with id " + id + " not found")
        );
        existingExhibition.setTitle(exhibition.getTitle());
        existingExhibition.setDescription(exhibition.getDescription());
        existingExhibition.setLocation(exhibition.getLocation());
        existingExhibition.setPrice(exhibition.getPrice());
        existingExhibition.setAvailableForRent(exhibition.isAvailableForRent());
        existingExhibition.setMaxCapacity(exhibition.getMaxCapacity());
        existingExhibition.setDate(exhibition.getDate());
//        existingExhibition.setUpdatedAt(exhibition.getUpdatedAt());
        exhibitionRepository.save(existingExhibition);
    }

    public void deleteExhibition(Integer id) {
        Exhibition existingExhibition = exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition with id " + id + " not found")
        );
        exhibitionRepository.delete(existingExhibition);
    }

    public void rentExhibitionForArtists(Integer exhibition_id, Integer artist_id) {
        Exhibition exhibition = getExhibitionById(exhibition_id);
        Artist artist = artistRepository.findArtistById(artist_id);
        if(exhibition == null) {
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");
        }if(artist == null){
            throw new ApiException("Artist with id " + artist_id + " not found");
        }
        exhibition.setAvailableForRent(true);
        exhibitionRepository.save(exhibition);
    }
}
