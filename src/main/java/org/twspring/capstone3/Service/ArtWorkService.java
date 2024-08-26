package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Repository.ArtWorkRepository;
import org.twspring.capstone3.Repository.ArtistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtWorkService {
    private final ArtWorkRepository artWorkRepository;
    private final ArtistRepository artistRepository;

    public List<ArtWork> getAllArtWork() {

        return artWorkRepository.findAll();
    }

    public void addArtWorkToArtist(Integer artist_id, ArtWork artWork) {
        Artist artist = artistRepository.findArtistById(artist_id);
        if (artist == null) {
            throw new ApiException("Artist not found");
        }
        artWork.setArtist(artist);
        artWorkRepository.save(artWork);

    }

    public void updateArtWork(Integer id, ArtWork artWork) {
        ArtWork artWork1 = artWorkRepository.findArtWorkById(id);
        if (artWork1 == null) {
            throw new ApiException("ArtWork not found");
        }
        artWork1.setDescription(artWork.getDescription());
        artWork1.setImageUrl(artWork.getImageUrl());
//        artWork1.setCreatedAt(artWork.getCreatedAt());
//        artWork1.setUpdatedAt(artWork.getUpdatedAt());
        artWorkRepository.save(artWork1);
    }

    public void deleteArtWork(Integer id) {
        ArtWork artWork = artWorkRepository.findArtWorkById(id);
        if (artWork == null) {
            throw new ApiException("ArtWork not found");

        }
        artWorkRepository.delete(artWork);
    }
}