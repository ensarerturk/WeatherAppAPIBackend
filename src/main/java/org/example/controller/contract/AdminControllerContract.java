package org.example.controller.contract;

import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;
import org.example.general.restresponse.RestResponse;

import java.util.List;

public interface AdminControllerContract {

    List<UserDTO> getAllUsers();
    List<AdminDTO> getAllAdmins();
    void deleteUserById(Long userId);
    void deleteAdminById(Long adminId);

    RestResponse<String> loginAdmin(AdminDTO adminDTO);
}
