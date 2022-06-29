package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.web.dto.UserDTO;
import com.bsokolovskyi.bridge.web.enums.Role;
import com.bsokolovskyi.bridge.web.enums.Sex;
import com.bsokolovskyi.bridge.web.exception.UserNotExistException;
import com.bsokolovskyi.bridge.web.request.RefreshAccessTokenRequest;
import com.bsokolovskyi.bridge.web.security.CustomUserDetails;
import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.UserRepository;
import com.bsokolovskyi.bridge.web.exception.IncorrectPasswordException;
import com.bsokolovskyi.bridge.web.exception.UserExistException;
import com.bsokolovskyi.bridge.web.security.jwt.JwtProvider;
import com.bsokolovskyi.bridge.web.request.AuthRequest;
import com.bsokolovskyi.bridge.web.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final NotificationService notificationService;

    private final static SimpleDateFormat DF = new SimpleDateFormat("dd.MM.yyyy");


    public UserService(@Autowired UserRepository userRepository,
                       @Autowired PasswordEncoder passwordEncoder,
                       @Autowired JwtProvider jwtProvider,
                       @Autowired NotificationService notificationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.notificationService = notificationService;

        //pre init admin (it is hard core, but for edu)
        if(userRepository.findByEmail("admin@localhost") == null) {
            User admin = new User();

            admin.setFirstName("Big");
            admin.setLastName("Admin");
            admin.setSex(Sex.OTHER);
            admin.setBirth(new Date());
            admin.setEmail("sokol.chemist@gmail.com");
            admin.setHashPassword(passwordEncoder.encode("i_am_very_big_admin"));
            admin.setRole(Role.ROLE_ADMIN);

            userRepository.save(admin);
        }
    }

    public void createNewUser(SignupRequest signupRequest) throws UserExistException, InterruptedException, MailException {
        if(userRepository.findByEmail(signupRequest.getEmail()) != null) {
            throw new UserExistException(signupRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setHashPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Role userRole = Objects.requireNonNull(Role.ROLE_USER);

        user.setRole(userRole);

        userRepository.save(user);

        notificationService.sendRegisterNotification(user);
    }

    public Map<String, String> loginUser(AuthRequest authRequest) throws
            UserNotExistException,
            IncorrectPasswordException,
            InterruptedException,
            MailException {
        User user = userRepository.findByEmail(authRequest.getEmail());

        if(user == null) {
            throw new UserNotExistException(authRequest.getEmail());
        }

        if(!passwordEncoder.matches(authRequest.getPassword(), user.getHashPassword())) {
            throw new IncorrectPasswordException(authRequest.getEmail());
        }

        Map<String, String> response = new HashMap<>();

        response.put("accessToken", jwtProvider.generateToken(user.getEmail()));
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("email", user.getEmail());
        response.put("sex", user.getSex().name());
        response.put("birth", DF.format(user.getBirth()));

        notificationService.sendLoginNotification(user);

        return response;
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
