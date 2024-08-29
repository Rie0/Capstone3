package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.DTO.ArtistProfileDTO;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Model.ArtistProfile;
import org.twspring.capstone3.Repository.ArtistProfileRepository;
import org.twspring.capstone3.Repository.ArtistRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistProfileService {
    private final ArtistProfileRepository artistProfileRepository;
    private final ArtistRepository artistRepository;
    public List<ArtistProfile> getAllArtistProfiles() {
        return artistProfileRepository.findAll();
    }



    //EP
    public void createArtistProfile(ArtistProfileDTO artistProfileDTO) {
        Artist artist=artistRepository.findArtistById(artistProfileDTO.getArtist_id());
        if(artist==null) {
            throw new ApiException("Artist not found");
        }
        ArtistProfile artistProfile=new ArtistProfile(null,artistProfileDTO.getBio(),artistProfileDTO.getUpdatedAt(),artist);
        artistProfileRepository.save(artistProfile);

    }

    public void updateArtistProfile(ArtistProfileDTO artistProfileDTO) {
        ArtistProfile   artistProfile     = artistProfileRepository.findArtistProfileById(artistProfileDTO.getArtist_id());
        if(artistProfile==null) {
            throw new ApiException("Artist not found");
        }
        artistProfile.setBio(artistProfileDTO.getBio());
        artistProfile.setUpdatedAt(LocalDateTime.now());
        artistProfileRepository.save(artistProfile);

    }

    public void deleteArtistProfile(Integer id) {
        ArtistProfile artistProfile = artistProfileRepository.findArtistProfileById(id);
        if (artistProfile == null) {
            throw new ApiException("Artist profile not found");
        }
        artistProfileRepository.delete(artistProfile);
    }
}

