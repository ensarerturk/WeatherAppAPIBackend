package org.example.service;

import org.example.model.Admin;
import org.example.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdminById(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    public boolean authenticateAdmin(String username, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            return admin.getPassword().equals(password);
        }
        return false;
    }
}
