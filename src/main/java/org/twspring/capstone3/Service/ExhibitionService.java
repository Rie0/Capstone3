package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Model.Organizer;
import org.twspring.capstone3.Repository.ArtistRepository;
import org.twspring.capstone3.Repository.ExhibitionRepository;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Repository.OrganizerRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ArtistRepository artistRepository;
    private final OrganizerRepository organizerRepository;

    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    //EP
    public void addExhibitionToOrganizer(Integer organizerId, Exhibition exhibition) {

        Organizer org= organizerRepository.getOrganizerById(organizerId);
        if (org==null){
            throw new ApiException("Organizer not found");
        }
        exhibition.setOrganizer(org);
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
        existingExhibition.setStartDate(exhibition.getStartDate());
        existingExhibition.setEndDate(exhibition.getEndDate());
//        existingExhibition.setUpdatedAt(exhibition.getUpdatedAt());
        exhibitionRepository.save(existingExhibition);
    }

    public void deleteExhibition(Integer id) {
        Exhibition existingExhibition = exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition with id " + id + " not found")
        );
        exhibitionRepository.delete(existingExhibition);
    }

    public void rentExhibitionForArtists(Integer artist_id, LocalDate startDate, LocalDate endDate) {
        Exhibition exhibition = exhibitionRepository.findExhibitionByStartDateBetween(startDate, endDate);
        Artist artist = artistRepository.findArtistById(artist_id);
        if(exhibition == null) {
            throw new ApiException("Exhibition is not available");
        }if(artist == null){
            throw new ApiException("Artist with id " + artist_id + " not found");}
        exhibition.setAvailableForRent(true);
        exhibitionRepository.save(exhibition);}
}
