package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Model.Admin;
import org.twspring.capstone3.Repository.AdminRepository;
import org.twspring.capstone3.Api.ApiException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void updateAdmin(Integer id, Admin admin) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(() ->
                new ApiException("Admin with id " + id + " not found")
        );
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Integer id) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(() ->
                new ApiException("Admin with id " + id + " not found")
        );
        adminRepository.delete(existingAdmin);
    }
}
