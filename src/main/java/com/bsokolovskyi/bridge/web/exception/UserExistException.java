package com.bsokolovskyi.bridge.web.exception;

import com.bsokolovskyi.bridge.web.dto.UserDTO;

public class UserExistException extends RuntimeException {

    public UserExistException(UserDTO userDTO) {
        super(String.format("user %s already exist", userDTO.getLogin()));
    }

}
