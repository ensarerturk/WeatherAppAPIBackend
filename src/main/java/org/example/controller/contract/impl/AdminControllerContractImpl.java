package org.example.controller.contract.impl;

import org.example.controller.contract.AdminControllerContract;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;
import org.example.general.restresponse.RestResponse;
import org.example.mapper.AdminMapper;
import org.example.mapper.UserMapper;
import org.example.model.Admin;
import org.example.model.User;
import org.example.service.AdminService;
import org.example.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminControllerContractImpl implements AdminControllerContract {

    private final UserEntityService userService;
    private final AdminService adminService;

    public AdminControllerContractImpl(UserEntityService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return UserMapper.INSTANCE.toDTOList(userList);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admin> adminList = adminService.getAllAdmins();
        return AdminMapper.INSTANCE.toDTOList(adminList);
    }

    @Override
    public void deleteUserById(Long userId) {
        userService.deleteUserById(userId);
    }

    @Override
    public void deleteAdminById(Long adminId) {
        adminService.deleteAdminById(adminId);
    }

    @Override
    public RestResponse<String> loginAdmin(AdminDTO adminDTO) {
        boolean authenticated = adminService.authenticateAdmin(adminDTO.username(), adminDTO.password());
        if (authenticated) {
            return RestResponse.success(HttpStatus.OK.value(), "Admin login successful");
        } else {
            return RestResponse.error(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password");
        }
    }
}
