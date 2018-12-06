package com.beertag.services;

import com.beertag.models.User;
import com.beertag.repositories.base.UsersRepository;
import com.beertag.services.base.UsersService;
import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;

import java.io.IOException;
import java.util.List;

public class HttpUsersService implements UsersService {
    private final UsersRepository mUsersRepository;
    private final Validator<User> mUserValidator;

    public HttpUsersService(UsersRepository usersRepository, Validator<User> userValidator) {
        mUsersRepository = usersRepository;
        mUserValidator = userValidator;
    }


    @Override
    public User createUser(User userToCreate) throws Exception {
        boolean isUserValid = mUserValidator.isItemValid(userToCreate);
        if (!isUserValid) {
            throw new Exception(Constants.ADD_OF_USER_FAIL_MESSAGE);
        }
        return mUsersRepository.createUser(userToCreate);
    }

    @Override
    public User getUserById(int userId) throws IOException {
        return mUsersRepository.getUserById(userId);
    }

    @Override
    public User updateUser(User userToUpdate, int userId) throws IOException {
        return mUsersRepository.updateUser(userToUpdate,userId);
    }

    @Override
    public List<User> getUsers() throws IOException {
        return mUsersRepository.getUsers();
    }
}
