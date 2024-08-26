package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    Organizer getOrganizerById(Integer id);
}
