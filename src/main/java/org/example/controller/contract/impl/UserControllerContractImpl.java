package org.example.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.general.handler.NotMatchException;
import org.example.general.handler.UserNotFoundException;
import org.example.model.User;
import org.example.controller.contract.UserControllerContract;
import org.example.dto.UserDTO;
import org.example.dto.UserSaveDTO;
import org.example.mapper.UserMapper;
import org.example.service.ApiKeyService;
import org.example.service.UserEntityService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService entityService;

    private final ApiKeyService apiKeyService;

    // Method to register user
    @Override
    public UserDTO save(UserSaveDTO userSaveDTO) {
        log.info("Saving user: {}", userSaveDTO.username());

        User entity = UserMapper.INSTANCE.toEntity(userSaveDTO);
        String username = entity.getUsername();
        String password = entity.getPassword();
        entity = entityService.registerUser(username, password);

        log.info("User saved: {}", entity.getUsername());

        return UserMapper.INSTANCE.toDTO(entity);
    }

    // Method to find user by username and return API key
    @Override
    public String findByUserName(UserSaveDTO userSaveDTO) {
        log.info("Finding user by username: {}", userSaveDTO.username());

        User entity = UserMapper.INSTANCE.toEntity(userSaveDTO);
        User userByUsername = entityService.findUserByUsername(entity.getUsername());

        if (userByUsername == null) {
            log.error("User not found");
            throw new UserNotFoundException();
        }
        if (!userByUsername.getPassword().equals(userSaveDTO.password())) {
            log.error("User and password do not match");
            throw new NotMatchException();
        }

        String apiKey = apiKeyService.getApiKey();
        log.info("API key found for user: {}", userSaveDTO.username());

        return apiKey;
    }
}
