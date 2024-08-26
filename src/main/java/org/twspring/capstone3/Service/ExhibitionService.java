package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.Exhibition;
import org.twspring.capstone3.Repository.ExhibitionRepository;
import org.twspring.capstone3.Api.ApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    public Exhibition getExhibitionById(Integer id) {
        return exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition not found")
        );
    }

    public void addExhibition(Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
    }

    public void updateExhibition(Integer id, Exhibition exhibition) {
        Exhibition existingExhibition = exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition with id " + id + " not found")
        );
        existingExhibition.setTitle(exhibition.getTitle());
        existingExhibition.setDescription(exhibition.getDescription());
        existingExhibition.setLocation(exhibition.getLocation());
        existingExhibition.setPrice(exhibition.getPrice());
        existingExhibition.setAvailable(exhibition.isAvailable());
        existingExhibition.setCapacity(exhibition.getCapacity());
        existingExhibition.setDate(exhibition.getDate());
//        existingExhibition.setUpdatedAt(exhibition.getUpdatedAt());
        exhibitionRepository.save(existingExhibition);
    }

    public void deleteExhibition(Integer id) {
        Exhibition existingExhibition = exhibitionRepository.findById(id).orElseThrow(() ->
                new ApiException("Exhibition with id " + id + " not found")
        );
        exhibitionRepository.delete(existingExhibition);
    }
}
