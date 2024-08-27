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

    //post
    //Extra
    public void issueExhibitionTicket(Integer artEnthusiast_id, Integer exhibition_id, ExhibitionTicket exhibitionTicket) {
        ArtEnthusiast ae = artEnthusiastRepository.getArtEnthusiastById(artEnthusiast_id);
        Exhibition e = exhibitionRepository.findExhibitionById(exhibition_id);
        if(ae == null){
            throw new ApiException("Art Enthusiast not found");
        }
        if(e == null){
            throw new ApiException("Exhibition not found");
        }
        exhibitionTicket.setArtEnthusiast(ae);
        exhibitionTicket.setExhibition(e);
        exhibitionRepository.save(e);
    }

    public void updateExhibitionTicket(Integer id, ExhibitionTicket exhibitionTicket) {
        ExhibitionTicket et = exhibitionTicketRepository.findExhibitionTicketById(id);
        if(et == null){
            throw new ApiException("Exhibition ticket not found");
        }
        et.setPurchaseDate(exhibitionTicket.getPurchaseDate());
//        et.setCreatedAt(exhibitionTicket.getCreatedAt());
//        et.setUpdatedAt(exhibitionTicket.getUpdatedAt());
        et.setAmount(exhibitionTicket.getAmount());
        exhibitionTicketRepository.save(et);
    }

    public void deleteExhibitionTicket(Integer id) {
        ExhibitionTicket et = exhibitionTicketRepository.findExhibitionTicketById(id);
        if(et == null){
            throw new ApiException("Exhibition ticket not found");}
        exhibitionTicketRepository.delete(et);
    }

}
