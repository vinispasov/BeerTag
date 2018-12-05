package com.beertag.utils.validators;


import com.beertag.models.User;
import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;

import java.util.Objects;

public class UserValidator implements Validator<User> {

    @Override
    public boolean isItemValid(User user) {
        return (!Objects.equals(user, null)) &&
                isUserNameValid(user)
                && isFirstNameValid(user)
                && isLastNameValid(user);
    }

    private boolean isUserNameValid(User user) {
        return user.getUserName().length() >= Constants.MIN_USERNAME_CHAR_COUNT &&
               user.getUserName().length()<= Constants.MAX_USERNAME_CHAR_COUNT;
    }

    private boolean isFirstNameValid(User user) {
        return user.getFirstName().length()>= Constants.MIN_FIRSTNAME_CHAR_COUNT &&
               user.getFirstName().length() <= Constants.MAX_FIRSTNAME_CHAR_COUNT;
    }

    private boolean isLastNameValid(User user) {
        return user.getLastName().length() >= Constants.MIN_LASTNAME_CHAR_COUNT &&
               user.getLastName().length() <= Constants.MAX_LASTNAME_CHAR_COUNT;
    }

}
