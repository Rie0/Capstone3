package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.twspring.capstone3.Model.ExhibitionTicket;

public interface ExhibitionTicketRepository extends JpaRepository<ExhibitionTicket, Integer> {

    ExhibitionTicket findExhibitionTicketById(Integer id);
}
