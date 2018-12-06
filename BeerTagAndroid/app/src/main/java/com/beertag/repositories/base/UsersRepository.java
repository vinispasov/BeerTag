package com.beertag.repositories.base;

import com.beertag.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersRepository {
    User createUser(User userToCreate) throws IOException;
    User getUserById(int userId) throws IOException;
    User updateUser(User userToUpdate,int userId) throws IOException;
    List<User> getUsers() throws IOException;
}
