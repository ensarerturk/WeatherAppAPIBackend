package org.example.controller.contract;

import org.example.dto.UserDTO;
import org.example.dto.UserSaveDTO;

public interface UserControllerContract {

    /**
     * Registers the user.
     *
     * @param userSaveDTO Information of the user to save
     * @return Returns the DTO representation of the registered user
     */
    UserDTO save(UserSaveDTO userSaveDTO);

    /**
     * Finds the user's API key.
     *
     * @param userSaveDTO API key is searched for the information of the user
     * @return User's API key
     */
    String findByUserName(UserSaveDTO userSaveDTO);
}
