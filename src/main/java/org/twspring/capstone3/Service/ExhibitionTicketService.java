package org.twspring.capstone3.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Model.ExhibitionTicket;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Repository.ExhibitionRepository;
import org.twspring.capstone3.Repository.ExhibitionTicketRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionTicketService {

    private final ExhibitionTicketRepository exhibitionTicketRepository;
    private final ArtEnthusiastRepository artEnthusiastRepository;
    private final ExhibitionRepository exhibitionRepository;



    public List<ExhibitionTicket> getAllExhibitionTickets() {
        return exhibitionTicketRepository.findAll();
    }

    //EP
    public void issueExhibitionTicket(Integer artEnthusiast_id, Integer exhibition_id) {

        Exhibition e = exhibitionRepository.findExhibitionById(exhibition_id);
        if (e == null) {
            throw new ApiException("Exhibition with id " + exhibition_id + " not found");
        }
        ArtEnthusiast enthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiast_id);
        if (enthusiast == null) {
            throw new ApiException("Art Enthusiast not found with id: " + artEnthusiast_id);
        }
        // Check if the exhibition is open
        if (!e.isOpen()) {
            throw new ApiException("Exhibition is not open for ticket sales.");
        }
        // Check if capacity allows for more tickets
        if (e.getCurrentCapacity() >= e.getMaxCapacity()) {
            throw new ApiException("Cannot purchase ticket: capacity reached.");
        }
        ExhibitionTicket ticket = new ExhibitionTicket();
        // Create and save the ticket
        ticket.setArtEnthusiast(enthusiast);
        ticket.setExhibition(e);
        ticket.setAmount(e.getTicketPrice()+e.getServiceFee());
        e.setCurrentCapacity(e.getCurrentCapacity() + 1);
        exhibitionTicketRepository.save(ticket);
        exhibitionRepository.save(e);
    }

    public void updateExhibitionTicket(Integer id, ExhibitionTicket exhibitionTicket) {
        ExhibitionTicket et = exhibitionTicketRepository.findExhibitionTicketById(id);
        if(et == null){
            throw new ApiException("Exhibition ticket not found");
        }
        et.setPurchaseDate(exhibitionTicket.getPurchaseDate());
        et.setUpdatedAt(LocalDateTime.now());
        et.setAmount(exhibitionTicket.getAmount());
        exhibitionTicketRepository.save(et);
    }

    public void deleteExhibitionTicket(Integer id) {
        ExhibitionTicket et = exhibitionTicketRepository.findExhibitionTicketById(id);
        if(et == null){
            throw new ApiException("Exhibition ticket not found");}
        exhibitionTicketRepository.delete(et);
    }
    //EP
    public void cancelTicket(Integer ticketId, Integer enthusiastId) {
        // Find the ticket
        ExhibitionTicket ticket = exhibitionTicketRepository.findById(ticketId)
                .orElseThrow(() -> new ApiException("Ticket not found with id: " + ticketId));
        // Check if the ticket belongs to the art enthusiast
        if (!ticket.getArtEnthusiast().getId().equals(enthusiastId)) {
            throw new ApiException("This ticket does not belong to the art enthusiast.");}
        //check if cancellation is allowed based on date is before local date.now
        Exhibition exhibition = ticket.getExhibition();
        if (exhibition.getStartDate().isBefore(LocalDate.now())) {
            throw new ApiException("Cannot cancel ticket: the exhibition has already started.");}
        // Remove the ticket from the repository
        exhibitionTicketRepository.delete(ticket);
        // Decrement the current capacity of the exhibition
        exhibition.setCurrentCapacity(exhibition.getCurrentCapacity() - 1);
        exhibitionRepository.save(exhibition);
    }
    //EP
    public List<ExhibitionTicket> getTicketsByArtEnthusiast(Integer artEnthusiast_id) {
        return exhibitionTicketRepository.findExhibitionTicketByArtEnthusiast_Id(artEnthusiast_id);
    }

    //EP
    public List<ExhibitionTicket> getTicketsByAmount(Double amount) {
        return exhibitionTicketRepository.findExhibitionTicketByAmount(amount);
    }

    //EP
    public List<ExhibitionTicket> getTicketsByExhibition(Integer exhibitionId) {
        return exhibitionTicketRepository.findExhibitionTicketByExhibition_Id(exhibitionId);
    }

}
