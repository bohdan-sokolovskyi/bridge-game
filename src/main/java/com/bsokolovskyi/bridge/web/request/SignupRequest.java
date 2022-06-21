package com.bsokolovskyi.bridge.web.request;

public class SignupRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("signup-req: {%s, %s, %s, %s}", firstName, lastName, email, password);
    }
}
