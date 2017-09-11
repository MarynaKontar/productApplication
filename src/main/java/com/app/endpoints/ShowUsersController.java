package com.app.endpoints;

import com.app.entities.User;
import com.app.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 21.08.2017.
 */
@Controller
@RequestMapping(value = "/user")
public class ShowUsersController {

    private UserService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ShowUsersController(UserService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public ModelAndView showUsers() {
        List<String> usernames =
                usersService.findAll().stream().map(User::getUsername).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", usernames);
        return modelAndView;
    }

    // http://localhost:8080/find/admin
    @RequestMapping(value = "/find/{login}")
    public ModelAndView findUser(@PathVariable("login") String login) {
        ModelAndView modelAndView = new ModelAndView();
        //if (!bindingResult.hasErrors()) {
        modelAndView.setViewName("user");
        // ${object}
        User user = usersService.findOne(login);
        if (user != null) {
            modelAndView.addObject("user", user);
        }
        //}
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/action/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<User> createUser(@RequestParam("username") @NotEmpty String username,
                                           @NotEmpty String password,
                                           @RequestParam("admin") boolean isAdmin,
                                           @RequestParam(required = false) String email)
//                                           @RequestHeader(value = "x", required = false) String x,
//                                           @CookieValue(value = "y", required = false) String y)
                                                                                    throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setAdministrator(isAdmin);
        //TODO 7. перед записью в бд шифруем пароль с помощью PasswordEncoder (найдет  @Bean BCryptPasswordEncoder
        // (implements PasswordEncoder), который объявили в SpringSecurityConfiguration). В методе encode добавляется hash и salt
        //Чтобы самому добавить salt, надо использовать метод BCrypt.hashpw(rawPassword.toString(), salt); ???
        user.setPassword(passwordEncoder.encode(password));
//        user.setPassword(BCrypt.hashpw(password.toString(), "xxxabc"));
        user.setEmail(email);
        usersService.save(user);

        return ResponseEntity.ok(user);
//    throw new IOException("IO test");

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        // http://localhost:8080/login?logout
        return "redirect:/login?logout";
    }

    @Component
    public static class UserValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            return true;
        }

        @Override
        public void validate(Object target, Errors errors) {
        }
    }
}
