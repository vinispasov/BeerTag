package com.beertag.services;

import com.beertag.models.User;
import com.beertag.repositories.base.UsersListRepository;
import com.beertag.services.base.UsersListService;
import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;

import java.io.IOException;
import java.util.List;

public class HttpUsersListService implements UsersListService {
    private final UsersListRepository mUsersListRepository;
    private final Validator<User> mUserValidator;

    public HttpUsersListService(UsersListRepository usersListRepository, Validator<User> userValidator) {
        mUsersListRepository = usersListRepository;
        mUserValidator = userValidator;
    }


    @Override
    public User createUser(User userToCreate) throws Exception {
        boolean isUserValid = mUserValidator.isItemValid(userToCreate);
        if (!isUserValid) {
            throw new Exception(Constants.ADD_OF_USER_FAIL_MESSAGE);
        }
        return mUsersListRepository.createUser(userToCreate);
    }

    @Override
    public User getUserById(int userId) throws IOException {
        return mUsersListRepository.getUserById(userId);
    }

    @Override
    public User updateUser(User userToUpdate, int userId) throws IOException {
        return mUsersListRepository.updateUser(userToUpdate,userId);
    }

    @Override
    public List<User> getUsers() throws IOException {
        return mUsersListRepository.getUsers();
    }
}
