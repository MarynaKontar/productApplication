package com.app.endpoints;

import com.app.entities.User;
import com.app.service.ProductService;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by User on 17.08.2017.
 */
@RestController
@RequestMapping("/product")
public class ProductEndpoints {

    private final UserService userService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProductEndpoints(UserService userService,
                            ProductService productService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
//    public void initDefaultUsers() {
//        User user1 = new User();
//        user1.setUsername("admin1");
//        user1.setPassword(passwordEncoder.encode("admin1"));
//        user1.setAdministrator(true);
//
//        userService.save(user1);
//
//        User user2 = new User();
//        user2.setUsername("userrr");
//        user2.setPassword(passwordEncoder.encode("userrr"));
//        user2.setAdministrator(false);
//
//        userService.save(user2);
//    }


}
