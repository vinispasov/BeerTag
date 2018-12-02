package com.beertag.beertagfinalproject.controllers;

import com.beertag.beertagfinalproject.models.User;
import com.beertag.beertagfinalproject.services.base.UsersService;
import com.beertag.beertagfinalproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.USERS_ROOT_MAPPING)
public class UsersApiController {
    private final UsersService usersService;

    @Autowired
    public UsersApiController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid User userToCreate) {
        return usersService.createUser(userToCreate);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int userId) {
        return usersService.getUserById(userId);

    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody @Valid User userToUpdate, @PathVariable int userId) {
        return usersService.updateUser(userToUpdate, userId);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return usersService.getUsers();
    }
}
