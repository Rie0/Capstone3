package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.Admin;
import org.twspring.capstone3.Model.Organizer;
import org.twspring.capstone3.Repository.AdminRepository;
import org.twspring.capstone3.Repository.OrganizerRepository;
import org.twspring.capstone3.Api.ApiException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final AdminRepository adminRepository;

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void addOrganizer(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    //EP
    public void approveOrganizer(Integer adminId, Integer organizerId) {
        Organizer organizer = organizerRepository.getOrganizerById(organizerId);
        if (organizer == null){
            throw new ApiException("Organizer not found");
        }
        Admin admin=adminRepository.findAdminById(adminId);
        if (admin == null){
            throw new ApiException("Admin not found");
        }
        if (organizer.getStatus()==Organizer.Status.APPROVED){
            throw new ApiException("Organizer already approved");
        }

        organizer.setStatus(Organizer.Status.APPROVED);
        organizerRepository.save(organizer);

    }
    //EP
    public void rejectOrganizer(Integer adminId, Integer organizerId) {
        Organizer organizer = organizerRepository.getOrganizerById(organizerId);
        if (organizer == null){
            throw new ApiException("Organizer not found");
        }
        Admin admin=adminRepository.findAdminById(adminId);
        if (admin == null){
            throw new ApiException("Admin not found");
        }
        if(organizer.getStatus()==Organizer.Status.REJECTED){
            throw new ApiException("Organizer already rejected");
        }
        organizer.setStatus(Organizer.Status.REJECTED);
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
        existingOrganizer.setUpdatedAt(LocalDateTime.now());
        organizerRepository.save(existingOrganizer);
    }

    public void deleteOrganizer(Integer id) {
        Organizer existingOrganizer = organizerRepository.findById(id).orElseThrow(() ->
                new ApiException("Organizer with id " + id + " not found")
        );
        organizerRepository.delete(existingOrganizer);
    }
}
