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
        existingExhibition.setPricePerDay(exhibition.getPricePerDay());
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
//EP
    public void rentExhibitionForArtist(Integer exhibition_id, Integer artist_id, LocalDate startDate, LocalDate endDate) {
            Exhibition exhibition = exhibitionRepository.findExhibitionById(exhibition_id);
            Artist artist = artistRepository.findArtistById(artist_id);
            if(exhibition == null) {
                throw new ApiException("Exhibition wit id " + exhibition_id + " not found");
            }
            if(artist == null){
                throw new ApiException("Artist with id " + artist_id + " not found");
            }
            // Check if the exhibition is available for the given date range
          if (exhibition.isAvailableForRent()) {
              exhibition.setAvailableForRent(false);
              exhibition.setStartDate(startDate);
              exhibition.setEndDate(endDate);
              exhibition.setOpen(true);
              exhibitionRepository.save(exhibition);
          } else {
             throw  new ApiException("Exhibition is rented");
          }
    }
    //END RENT
    public  void endRentExhibition(Integer exhibition_id, Integer organizer_id) {
        Exhibition e = exhibitionRepository.findExhibitionById(exhibition_id);
        Organizer o = organizerRepository.getOrganizerById(organizer_id);
        if(e == null){
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");
        }if(o==null){
            throw new ApiException("Organizer with id " + organizer_id + " not found");
        }if(e.getEndDate().isAfter(LocalDate.now())){
            throw new ApiException("Cannot end exhibition before end date");
        }if(e.isAvailableForRent()){
            throw new ApiException("Exhibition is not rented");
        }
        // Set the exhibition as available for rent again
        e.setAvailableForRent(true);
        e.setStartDate(null);
        e.setEndDate(null);
        e.setCurrentCapacity(0);
        e.setOpen(false);
        exhibitionRepository.save(e); }
    //EXTEND RENT
    //CANCEL RENT(User cancels rent before end date)
}
