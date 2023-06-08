package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@Api(tags = "Admin", description = "Admin Operation")
public class AdminController {

    private final AdminControllerContract controllerContract;
    private final UserEntityService userService;
    private final AdminService adminService;

    public AdminController(AdminControllerContract controllerContract, UserEntityService userService, AdminService adminService) {
        this.controllerContract = controllerContract;
        this.userService = userService;
        this.adminService = adminService;
    }

    @PostMapping("/login")
    @ApiOperation("Admin login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<RestResponse<String>> loginAdmin(@RequestBody AdminDTO adminDTO) {
        log.info("Admin login");

        RestResponse<String> response = controllerContract.loginAdmin(adminDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/users")
    @ApiOperation("List all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Getting all users");

        List<UserDTO> userDTOList = controllerContract.getAllUsers();
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/admins")
    @ApiOperation("List all admins")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<RestResponse<List<AdminDTO>>> getAllAdmins() {
        log.info("Getting all admins");

        List<Admin> adminList = adminService.getAllAdmins();
        List<AdminDTO> adminDTOList = AdminMapper.INSTANCE.toDTOList(adminList);

        return ResponseEntity.ok(RestResponse.success(HttpStatus.OK.value(), adminDTOList));
    }

    @DeleteMapping("/users/{userId}")
    @ApiOperation("Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<RestResponse<Object>> deleteUserById(@PathVariable Long userId) {
        log.info("Deleting user with ID: {}", userId);

        controllerContract.deleteUserById(userId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admins/{adminId}")
    @ApiOperation("Delete admin by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<RestResponse<Object>> deleteAdminById(@PathVariable Long adminId) {
        log.info("Deleting admin with ID: {}", adminId);

        adminService.deleteAdminById(adminId);

        return ResponseEntity.noContent().build();
    }
}
