package org.example.controller;

import org.example.controller.UserController;
import org.example.controller.contract.UserControllerContract;
import org.example.dto.UserDTO;
import org.example.dto.UserSaveDTO;
import org.example.general.restresponse.RestResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @Mock
    private UserControllerContract controllerContract;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        // Hazırlık
        UserSaveDTO userSaveDTO = new UserSaveDTO("ensar", "123");

        UserDTO userDTO = new UserDTO(1L, "ensat", "456");


        when(controllerContract.save(userSaveDTO)).thenReturn(userDTO);

        // Çalıştırma
        ResponseEntity<RestResponse<UserDTO>> response = userController.save(userSaveDTO);

        // Doğrulama
        Assertions.assertEquals(userDTO, Objects.requireNonNull(response.getBody()).getData());
    }
}