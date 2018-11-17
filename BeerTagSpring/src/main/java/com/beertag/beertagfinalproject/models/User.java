package com.beertag.beertagfinalproject.models;

import com.beertag.beertagfinalproject.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = Constants.USERS_TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USERS_TABLE_ID_COLUMN_NAME)
    private int userId;

    @NotNull
    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.USERS_TABLE_USER_NAME_COLUMN)
    private String userName;

    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.USERS_TABLE_USER_PASSWORD_COLUMN)
    private String userPassword;

    @NotNull
    @Size(min = Constants.FIRST_LAST_NAME_MIN_LENGTH, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.USERS_TABLE_USER_FIRST_NAME_COLUMN)
    private String firstName;

    @NotNull
    @Size(min = Constants.FIRST_LAST_NAME_MIN_LENGTH, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.USERS_TABLE_USER_LAST_NAME_COLUMN)
    private String lastName;

    @Column(name = Constants.USERS_TABLE_USER_PICTURE_COLUMN)
    private String userPicture;


    public User() {

    }

    public User(String userName, String userPassword, String firstName, String lastName,String userPicture) {
        setUserName(userName);
        setUserPassword(userPassword);
        setFirstName(firstName);
        setLastName(lastName);
        setUserPicture(userPicture);
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

}
