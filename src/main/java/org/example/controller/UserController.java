package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.contract.UserControllerContract;
import org.example.dto.UserDTO;
import org.example.dto.UserSaveDTO;
import org.example.general.restresponse.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "User", description = "User Operations")
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserControllerContract controllerContract;

    public UserController(UserControllerContract controllerContract) {
        this.controllerContract = controllerContract;
    }

    @ApiOperation(value = "Get API Key", notes = "Get the API Key for a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/api-key")
    public ResponseEntity<RestResponse<String>> getMyAPIKey(@RequestBody UserSaveDTO userSaveDTO) {
        log.info("Getting API Key for user: {}", userSaveDTO.username());

        String apiKey = controllerContract.findByUserName(userSaveDTO);

        log.info("API Key retrieved successfully");

        return ResponseEntity.ok(RestResponse.success(HttpStatus.OK.value(), apiKey));
    }

    @ApiOperation(value = "Register User", notes = "Register a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/register")
    public ResponseEntity<RestResponse<UserDTO>> save(@RequestBody UserSaveDTO userSaveDTO) {
        log.info("Registering a new user");

        UserDTO save = controllerContract.save(userSaveDTO);

        log.info("User registered successfully");

        return ResponseEntity.ok(RestResponse.success(HttpStatus.OK.value(), save));
    }
}

