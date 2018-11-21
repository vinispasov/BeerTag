package com.beertag.beertagfinalproject.repositories.base;

import com.beertag.beertagfinalproject.models.User;

import java.util.List;

public interface UsersRepository {
    User createUser(User userToCreate);
    User getUserById(int userId);
    User updateUser(User userToUpdate,int userId);
    List<User> getUsers();
}
