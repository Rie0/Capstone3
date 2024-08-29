package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.ArtEnthusiast;
import org.twspring.capstone3.Model.ArtWork;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Repository.ArtEnthusiastRepository;
import org.twspring.capstone3.Repository.ArtWorkRepository;
import org.twspring.capstone3.Repository.ArtistRepository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtWorkService {
    private final ArtWorkRepository artWorkRepository;
    private final ArtistRepository artistRepository;
    private final ArtEnthusiastRepository artEnthusiastRepository;

    public List<ArtWork> getAllArtWork() {
        return artWorkRepository.findAll();
    }

    //EP
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
        artWork1.setUpdatedAt(LocalDateTime.now());
        artWorkRepository.save(artWork1);
    }

    public void deleteArtWork(Integer id) {
        ArtWork artWork = artWorkRepository.findArtWorkById(id);
        if (artWork == null) {
            throw new ApiException("ArtWork not found");

        }
        artWorkRepository.delete(artWork);
    }
    //EP
    public void likeArtWork(Integer artEnthusiastId, Integer artWorkId) {
        ArtEnthusiast artEnthusiast = artEnthusiastRepository.getArtEnthusiastById(artEnthusiastId);
        if(artEnthusiast == null) {
            throw new ApiException("ArtEnthusiast not found");
        }

        ArtWork artWork = artWorkRepository.findArtWorkById(artWorkId);
        if(artEnthusiast == null) {
            throw new ApiException("ArtWork not found");
        }

        if (artEnthusiast.getLikedArtWorks().contains(artWork)) {
            throw new ApiException("You have already liked this artwork.");
        }
        artWork.getLikedBy().add(artEnthusiast);
        artWork.setLikeCount(artWork.getLikeCount() + 1);
        artWorkRepository.save(artWork);

        artEnthusiast.getLikedArtWorks().add(artWork);
        artEnthusiastRepository.save(artEnthusiast);
    }

    //EP
    public int getLikeCount(Integer artWorkId) {
        ArtWork artWork = artWorkRepository.findArtWorkById(artWorkId);
        if (artWork == null) {
            throw new ApiException("ArtWork not found");
        }
        return artWork.getLikeCount();
    }
    public List<ArtWork> getPopularArtWorks(Integer limit) {
        List<ArtWork> allArtWorks = artWorkRepository.findAll();

        // Sort artworks by likeCount in descending order
        allArtWorks.sort((a, b) -> b.getLikeCount() - a.getLikeCount());

        // If a limit is provided, return only that number of artworks
        if (limit != null && limit > 0) {
            return allArtWorks.stream().limit(limit).collect(Collectors.toList());
        }

        return allArtWorks;
    }

    //EP
    public List<ArtWork> getArtworksByArtist(Integer artistId) {
        Artist artist = artistRepository.findArtistById(artistId);
        if (artist == null) {
            throw new ApiException("Artist not found");
        }
        return artWorkRepository.findByArtist(artist);
    }

    //EP
    public List<ArtWork> searchArtWorks(String keyword) {
        return artWorkRepository.searchArtWorksByKeyword(keyword);
    }

}