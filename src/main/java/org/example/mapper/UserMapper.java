package org.example.mapper;

import org.example.model.User;
import org.example.dto.UserDTO;
import org.example.dto.UserSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Converts UserSaveDTO to User object.
     *
     * @param userSaveDTO UserSaveDTO object
     * @return User object
     */
    User toEntity(UserSaveDTO userSaveDTO);

    /**
     * Converts the User object to UserDTO.
     *
     * @param user User object
     * @return UserDTO object
     */
    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> userList);
}
