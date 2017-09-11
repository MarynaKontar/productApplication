package com.app.endpoints;

import com.app.entities.User;
import com.app.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by User on 19.08.2017.
 */
@RestController
@RequestMapping("/user")
public class UserEndpoints {

    private final UserService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEndpoints(UserService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
//    public void initDefaultUsers() {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setAdministrator(true);
//        usersService.save(user);
//
//
//        user = new User();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("userpassword"));
//        user.setAdministrator(false);
//        usersService.save(user);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String listAllUsers() {
        return usersService.findAll().stream().map(User::getUsername).collect(Collectors.joining(";"));
    }



    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }

//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<String> handleException(Exception ex) {
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//  }

}

