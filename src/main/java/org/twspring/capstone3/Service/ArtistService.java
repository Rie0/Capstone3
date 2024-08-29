package org.twspring.capstone3.Service;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.Artist;
import org.twspring.capstone3.Repository.ArtWorkRepository;
import org.twspring.capstone3.Repository.ArtistRepository;
import org.twspring.capstone3.Repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ShopRepository shopRepository;
    private final ArtWorkRepository artWorkRepository;


    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public void updateArtist(Integer id,Artist artist) {
        Artist artist1= artistRepository.findArtistById(id);
        if(artist1 == null) {
            throw new ApiException("Artist not found");
        }
        artist1.setEmail(artist.getEmail());
        artist1.setPassword(artist.getPassword());
        artist1.setUpdatedAt(LocalDateTime.now());
        artistRepository.save(artist1);
    }

    public void deleteArtist(Integer id) {
        Artist artist = artistRepository.findArtistById(id);
        if(artist == null) {
            throw new ApiException("Artist not found");
        }
        artistRepository.delete(artist);
    }
}
