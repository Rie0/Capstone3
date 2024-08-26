package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.Organizer;
import org.twspring.capstone3.Repository.OrganizerRepository;
import org.twspring.capstone3.Api.ApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Organizer getOrganizerById(Integer id) {
        return organizerRepository.findById(id).orElseThrow(() ->
                new ApiException("Organizer not found")
        );
    }

    public void addOrganizer(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    public void updateOrganizer(Integer id, Organizer organizer) {
        Organizer existingOrganizer = organizerRepository.findById(id).orElseThrow(() ->
                new ApiException("Organizer with id " + id + " not found")
        );
        existingOrganizer.setUsername(organizer.getUsername());
        existingOrganizer.setPassword(organizer.getPassword());
        existingOrganizer.setEmail(organizer.getEmail());
        existingOrganizer.setStatus(organizer.getStatus());
//        existingOrganizer.setUpdatedAt(organizer.getUpdatedAt());
        organizerRepository.save(existingOrganizer);
    }

    public void deleteOrganizer(Integer id) {
        Organizer existingOrganizer = organizerRepository.findById(id).orElseThrow(() ->
                new ApiException("Organizer with id " + id + " not found")
        );
        organizerRepository.delete(existingOrganizer);
    }
}
