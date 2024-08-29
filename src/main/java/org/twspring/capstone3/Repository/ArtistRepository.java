package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Artist findArtistById(Integer id);
}
