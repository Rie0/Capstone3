package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.twspring.capstone3.Model.ExhibitionTicket;

import java.util.List;

public interface ExhibitionTicketRepository extends JpaRepository<ExhibitionTicket, Integer> {

    ExhibitionTicket findExhibitionTicketById(Integer id);

    List<ExhibitionTicket> findExhibitionTicketByArtEnthusiast_Id(Integer artEnthusiast_id);

    List<ExhibitionTicket> findExhibitionTicketByAmount(Double amount);

    List<ExhibitionTicket> findExhibitionTicketByExhibition_Id(Integer exhibitionId);
}
