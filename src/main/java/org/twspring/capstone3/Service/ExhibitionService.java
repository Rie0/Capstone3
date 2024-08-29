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
import java.time.LocalDateTime;
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
        if (org.getStatus() != Organizer.Status.APPROVED){
            throw new ApiException("Organizer is not approved, only approved organizers can post exhibitions");
        }
        exhibition.setOrganizer(org);
        exhibition.setStartDate(null);
        exhibition.setEndDate(null);
        exhibition.setAvailableForRent(true);
        exhibitionRepository.save(exhibition);
    }

    public void updateExhibition(Integer id, Exhibition exhibition) {
        Exhibition existingExhibition = exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition with id " + id + " not found")
        );
        existingExhibition.setTitle(exhibition.getTitle());
        existingExhibition.setDescription(exhibition.getDescription());
        existingExhibition.setLocation(exhibition.getLocation());
        existingExhibition.setAvailableForRent(exhibition.isAvailableForRent());
        existingExhibition.setMaxCapacity(exhibition.getMaxCapacity());
        existingExhibition.setServiceFee(exhibition.getServiceFee());
        existingExhibition.setStartDate(exhibition.getStartDate());
        existingExhibition.setEndDate(exhibition.getEndDate());
        existingExhibition.setTicketPrice(exhibition.getTicketPrice());
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
    public void rentExhibitionForArtist(Integer exhibition_id, Integer artist_id, LocalDate startDate, LocalDate endDate, double ticketPrice) {
        Exhibition e = exhibitionRepository.findExhibitionById(exhibition_id);
        Artist artist = artistRepository.findArtistById(artist_id);

        if (e == null) {
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");
        }
        if (artist == null) {
            throw new ApiException("Artist with id " + artist_id + " not found");}
        // Ensure the exhibition is available for the given date range
        if (e.isAvailableForRent()) {
            e.setAvailableForRent(false);
            e.setStartDate(startDate);
            e.setEndDate(endDate);
            e.setOpen(true);
            e.setTicketPrice(ticketPrice);
            e.setArtist(artist);
            exhibitionRepository.save(e);
            artistRepository.save(artist);
        } else {
            throw new ApiException("Exhibition is rented or the dates overlap with an existing rental.");
        }
    }

    //EP
    //END RENT
    public  void endRentExhibition(Integer exhibition_id, Integer organizer_id) {
        Exhibition e = exhibitionRepository.findExhibitionById(exhibition_id);
        Organizer o = organizerRepository.getOrganizerById(organizer_id);
        if(e == null){
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");
        }if(o==null){
            throw new ApiException("Organizer with id " + organizer_id + " not found");
        }if(e.isAvailableForRent()){
            throw new ApiException("Exhibition is not rented");
        }
        if(e.getEndDate() != null && e.getEndDate().isBefore(LocalDate.now())){
            throw new ApiException("Cannot end exhibition before end date");
        }
        // Set the exhibition as available for rent again
        e.setAvailableForRent(true);
        e.setStartDate(null);
        e.setEndDate(null);
        e.setCurrentCapacity(0);
        e.setTicketPrice(0);
        e.setOpen(false);
        e.setArtist(null);
        exhibitionRepository.save(e); }

    //EP
    //EXTEND RENT
    public void extendExhibition(Integer exhibition_id, Integer organizer_id, LocalDate newEndDate) {
        Exhibition exhibition = exhibitionRepository.findExhibitionById(exhibition_id);
        Organizer organizer = organizerRepository.getOrganizerById(organizer_id);
        if (exhibition == null) {
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");}
        if (organizer == null) {
            throw new ApiException("Organizer with id " + organizer_id + " not found");}
        // Ensure the new end date is after the current end date
        if (newEndDate.isBefore(exhibition.getEndDate()) || newEndDate.isEqual(exhibition.getEndDate())) {
            throw new ApiException("New end date must be after the current end date");}
        // Update the end date of the exhibition
        exhibition.setEndDate(newEndDate);
        exhibitionRepository.save(exhibition);}

    //CANCEL RENT(User cancels rent before end date)
        public void cancelExhibitionForArtist(Integer exhibitionId, Integer artistId) {
        Exhibition e = exhibitionRepository.findExhibitionById(exhibitionId);
        Artist artist = artistRepository.findArtistById(artistId);
        if (e == null) {
            throw new ApiException("Exhibition with id " + exhibitionId + " not found");}
        if (artist == null) {
            throw new ApiException("Artist with id " + artistId + " not found");}
        // Check if the artist is the one who rented the exhibition
       if (!artist.getId().equals(artistId)) {
            throw new ApiException("Only the artist who rented the exhibition can cancel it.");}
        // Check if the current date is before the exhibition's end date
        if (e.getEndDate().isBefore(LocalDate.now())) {
            throw new ApiException("Cannot cancel exhibition: the exhibition has already ended.");
        }
        // Mark the exhibition as canceled or perform the cancellation
            e.setAvailableForRent(true);
            e.setStartDate(null);
            e.setEndDate(null);
            e.setCurrentCapacity(0);
            e.setTicketPrice(0);
            e.setOpen(false);
            e.setArtist(null);
        exhibitionRepository.save(e);
    }

    public List<Exhibition> getExhibitionsByAvailability(Boolean isAvailableForRent) {
        return exhibitionRepository.findExhibitionByIsAvailableForRent(isAvailableForRent);
    }

}
