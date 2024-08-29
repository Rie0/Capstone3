package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Model.Artist;

import java.util.List;
@Repository
public interface ArtWorkRepository extends JpaRepository<ArtWork, Integer> {
    ArtWork findArtWorkById(Integer id);
    List<ArtWork> findByArtist(Artist artist);

    @Query("SELECT a FROM ArtWork a WHERE LOWER(a.description) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(a.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ArtWork> searchArtWorksByKeyword(@Param("keyword") String keyword);

}
