package org.example.service;

import org.junit.jupiter.api.Test;

import org.example.general.handler.NotFoundException;
import org.example.model.City;
import org.example.model.ForecastData;
import org.example.model.User;
import org.example.repository.CityRepository;
import org.example.repository.ForecastDataRepository;
import org.example.repository.UserRepository;
import org.example.service.UserEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserEntityServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ForecastDataRepository forecastDataRepository;

    private UserEntityService userEntityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userEntityService = new UserEntityService(userRepository, userRepository, cityRepository, forecastDataRepository);
    }

    @Test
    public void testRegisterUser() {
        String username = "ensar";
        String password = "password";

        User existingUser = new User();
        existingUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User registeredUser = userEntityService.registerUser(username, password);

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(any(User.class));
        Assertions.assertEquals(username, registeredUser.getUsername());
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() {
        String username = "ensar";
        String password = "password";

        User existingUser = new User();
        existingUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(existingUser);

        Assertions.assertThrows(NotFoundException.class, () -> {
            userEntityService.registerUser(username, password);
        });
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testFindUserByUsername() {
        String username = "ensar";

        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(user);

        User foundUser = userEntityService.findUserByUsername(username);

        verify(userRepository, times(1)).findByUsername(username);
        Assertions.assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testFindUserByUsername_UserNotFound() {
        String username = "ensar";

        when(userRepository.findByUsername(username)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> {
            userEntityService.findUserByUsername(username);
        });
        verify(userRepository, times(1)).findByUsername(username);
    }
}