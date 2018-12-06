package com.beertag.services.base;

import com.beertag.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersService {
    User createUser(User userToCreate) throws Exception;
    User getUserById(int userId) throws IOException;
    User updateUser(User userToUpdate,int userId) throws IOException;
    List<User> getUsers() throws IOException;
}
