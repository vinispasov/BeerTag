package com.beertag.utils.mappers.base;

import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.DTO.UserDTO;
import com.beertag.models.User;

import java.util.List;

public interface UsersMapper {
    UserDTO mapUserToDTO(User user, List<BeerDTO> beersDTO);
}
