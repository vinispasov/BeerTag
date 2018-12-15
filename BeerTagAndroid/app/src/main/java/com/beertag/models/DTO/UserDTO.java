package com.beertag.models.DTO;

import java.util.List;

public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String userPicture;
    private List<BeerDTO> beers;

    public UserDTO() {

    }

    public UserDTO(String firstName, String lastName, String userName, String userPicture,List<BeerDTO> beers) {
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setUserPicture(userPicture);
        setBeers(beers);
    }
    public UserDTO(int userId,String firstName, String lastName, String userName, String userPicture,List<BeerDTO> beers) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setUserPicture(userPicture);
        setBeers(beers);
    }

    public int getUserId() {
        if (userId==0){
            userId=1;
        }
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

    public List<BeerDTO> getBeers() {
        return beers;
    }

    public void setBeers(List<BeerDTO> beers) {
        this.beers = beers;
    }
}
