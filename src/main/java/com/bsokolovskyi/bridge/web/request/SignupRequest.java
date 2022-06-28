package com.bsokolovskyi.bridge.web.request;

import com.bsokolovskyi.bridge.web.enums.Sex;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SignupRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Sex sex;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;
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

    public Sex getSex() {
        return sex;
    }

    public Date getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("signup-req: {%s, %s, %s, %s}", firstName, lastName, email, password);
    }
}
