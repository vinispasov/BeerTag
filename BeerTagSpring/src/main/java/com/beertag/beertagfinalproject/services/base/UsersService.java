package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.User;

import java.util.List;

public interface UsersService {
    User createUser(User userToCreate);
    User getUserById(int userId);
    User updateUser(User userToUpdate,int userId);
    List<User> getUsers();
}
