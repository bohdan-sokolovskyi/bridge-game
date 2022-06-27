package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.web.dto.UserDTO;
import com.bsokolovskyi.bridge.web.exception.UserNotExistException;
import com.bsokolovskyi.bridge.web.request.RefreshAccessTokenRequest;
import com.bsokolovskyi.bridge.web.security.CustomUserDetails;
import com.bsokolovskyi.bridge.web.db.entity.Role;
import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.RoleRepository;
import com.bsokolovskyi.bridge.web.db.repository.UserRepository;
import com.bsokolovskyi.bridge.web.exception.IncorrectPasswordException;
import com.bsokolovskyi.bridge.web.exception.UserExistException;
import com.bsokolovskyi.bridge.web.security.jwt.JwtProvider;
import com.bsokolovskyi.bridge.web.request.AuthRequest;
import com.bsokolovskyi.bridge.web.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired RoleRepository roleRepository,
                       @Autowired PasswordEncoder passwordEncoder,
                       @Autowired JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;

        //pre init data (it is hard core, but for edu)
        {
            roleRepository.save(new Role("ROLE_USER"));
            roleRepository.save(new Role("ROLE_ADMIN"));

            User admin = new User();

            admin.setFirstName("Big");
            admin.setLastName("Admin");
            admin.setEmail("admin@localhost");
            admin.setHashPassword(passwordEncoder.encode("i_am_very_big_admin"));
            admin.setRole(Objects.requireNonNull(roleRepository.findByRole("ROLE_ADMIN")));

            userRepository.save(admin);
        }
    }

    public void createNewUser(SignupRequest signupRequest) throws UserExistException {
        if(userRepository.findByEmail(signupRequest.getEmail()) != null) {
            throw new UserExistException(signupRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setHashPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Role userRole = Objects.requireNonNull(roleRepository.findByRole("USER"));

        user.setRole(userRole);

        userRepository.save(user);
    }

    public String loginUser(AuthRequest authRequest) throws UserNotExistException, IncorrectPasswordException {
        User user = userRepository.findByEmail(authRequest.getEmail());

        if(user == null) {
            throw new UserNotExistException(authRequest.getEmail());
        }

        if(!passwordEncoder.matches(authRequest.getPassword(), user.getHashPassword())) {
            throw new IncorrectPasswordException(authRequest.getEmail());
        }

        return jwtProvider.generateToken(user.getEmail());
    }

    public String refreshAccessToken(RefreshAccessTokenRequest refreshAccessTokenRequest) throws UserNotExistException {
         String email = jwtProvider.getEmailFromToken(refreshAccessTokenRequest.getAccessToken());

         if(userRepository.findByEmail(email) == null) {
             throw new UserNotExistException(email);
         }

         return jwtProvider.generateToken(email);
    }

    public Map<String, UserDTO> getAllUsersInfo() {
        return userRepository.findAll().stream().collect(Collectors.toMap(User::getEmail, UserDTO::new));
    }

    public UserDTO getUserInfo(String email) throws UserNotExistException {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UserNotExistException(email);
        }

        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("user by email %s not found", email));
        }

        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
