package com.beertag.beertagfinalproject.services;

import com.beertag.beertagfinalproject.models.User;
import com.beertag.beertagfinalproject.repositories.base.UsersRepository;
import com.beertag.beertagfinalproject.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }


    @Override
    public User createUser(User userToCreate) {
        return usersRepository.createUser(userToCreate);
    }

    @Override
    public User getUserById(int userId) {
        return usersRepository.getUserById(userId);
    }

    @Override
    public User updateUser(User userToUpdate, int userId) {
        return usersRepository.updateUser(userToUpdate,userId);
    }

    @Override
    public List<User> getUsers() {
        return usersRepository.getUsers();
    }
}
