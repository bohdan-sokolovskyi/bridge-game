package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.UserRepository;
import com.bsokolovskyi.bridge.web.dto.UserDTO;
import com.bsokolovskyi.bridge.web.exception.IncorrectPasswordException;
import com.bsokolovskyi.bridge.web.exception.LoginException;
import com.bsokolovskyi.bridge.web.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void login(UserDTO userDTO) throws LoginException, IncorrectPasswordException {
        User user = userRepository.findByLogin(userDTO.getLogin());

        if(user == null) {
            throw new LoginException(userDTO);
        }

        if(!user.getHashPassword().equals(passwordEncoder.encode(userDTO.getPassword()))) {
            throw new IncorrectPasswordException(userDTO);
        }

        //TODO: set status login
    }

    public void register(UserDTO userDTO) throws RegistrationException {
        User user = userRepository.findByLogin(userDTO.getLogin());

        if(user != null) {
            throw new RegistrationException(userDTO);
        }

        User newUser = new User();

        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setLogin(userDTO.getLogin());
        newUser.setHashPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(newUser);
    }

    public void logout(User user) {
        //TODO: implement me
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
