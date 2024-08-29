package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.ArtistProfile;
@Repository
public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Integer> {
    ArtistProfile findArtistProfileById(Integer id);

}
