package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.ArtWork;
@Repository
public interface ArtWorkRepository extends JpaRepository<ArtWork, Integer> {
    ArtWork findArtWorkById(Integer id);

}
