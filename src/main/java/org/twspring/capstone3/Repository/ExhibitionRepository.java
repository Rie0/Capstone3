package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.Exhibition;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {
    Exhibition findExhibitionById(Integer id);

   Exhibition findExhibitionByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
