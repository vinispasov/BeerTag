package com.beertag.utils.mappers;

import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.DTO.UserDTO;
import com.beertag.models.User;
import com.beertag.utils.mappers.base.UsersMapper;

import java.util.List;
import java.util.Objects;

public class UsersMapperImpl implements UsersMapper{


    @Override
    public UserDTO mapUserToDTO(User user, List<BeerDTO> beersDTO) {
        UserDTO userDTO = null;
        if (!Objects.equals(user,null)) {

            userDTO = new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserPicture(),
                    beersDTO
            );
        }
        return userDTO;
    }

}
