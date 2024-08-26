package org.twspring.capstone3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone3.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminById(Integer id);
}
