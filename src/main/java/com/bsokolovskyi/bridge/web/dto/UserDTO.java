package com.bsokolovskyi.bridge.web.dto;

import com.bsokolovskyi.bridge.web.db.entity.User;

public class UserDTO {

    private String email;
    private String firstName;
    private String lastName;

    public UserDTO() {}

    public UserDTO(User user) {
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
