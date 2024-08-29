package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.Exhibition;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {
    Exhibition findExhibitionById(Integer id);

    //List<Exhibition> findExhibitionByArtist(Integer artist_id);

    List<Exhibition> findExhibitionByIsAvailableForRent(Boolean isAvailableForRent);




}
