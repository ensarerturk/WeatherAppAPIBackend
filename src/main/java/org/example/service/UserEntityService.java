package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.general.BaseEntityService;
import org.example.general.handler.ThisUserAlreadyExistsException;
import org.example.general.handler.UserNotFoundException;
import org.example.model.User;
import org.example.repository.CityRepository;
import org.example.repository.ForecastDataRepository;
import org.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final ForecastDataRepository forecastDataRepository;

    @Autowired
    public UserEntityService(UserRepository repository, UserRepository userRepository, CityRepository cityRepository, ForecastDataRepository forecastDataRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.forecastDataRepository = forecastDataRepository;
    }

    /**
     * Registers a new user.
     *
     * @param username Username
     * @param password Password
     * @return Registered user
     */
    public User registerUser(String username, String password) {
        log.info("Registering user: {}", username);

        User existingUser = getRepository().findByUsername(username);
        if (existingUser != null) {
            log.error("User already exists: {}", username);
            throw new ThisUserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User userSave = save(user);
        log.info("User registered: {}", user.getUsername());

        return userSave;
    }

    /**
     * Finds user by username.
     *
     * @param username Username
     * @return Found user
     */
    public User findUserByUsername(String username){
        log.info("Finding user by username: {}", username);

        User byUsername = getRepository().findByUsername(username);
        if (byUsername == null){
            log.error("User not found: {}", username);
            throw new UserNotFoundException();
        }
        log.info("User found: {}", byUsername.getUsername());

        return byUsername;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}