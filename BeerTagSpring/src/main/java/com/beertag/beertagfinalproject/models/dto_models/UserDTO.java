package com.beertag.beertagfinalproject.models.dto_models;

public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String userPicture;

    public UserDTO() {

    }

    public UserDTO(int userId, String firstName, String lastName, String userName, String userPicture) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setUserPicture(userPicture);
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }
}
