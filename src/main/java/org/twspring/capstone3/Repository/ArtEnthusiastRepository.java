package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.ArtEnthusiast;

@Repository
public interface ArtEnthusiastRepository extends JpaRepository<ArtEnthusiast, Integer> {
    ArtEnthusiast getArtEnthusiastById(Integer id);

}
