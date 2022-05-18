package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }
//    @GetMapping("/create-user")
//    @ResponseBody
//    public String createUser() {
//        User user = new User();
//        user.setUserName("admin");
//        user.setPassword("admin");
//        userService.saveUser(user);
//        return "admin";
//    }
    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {
        log.info("customUser class {} " , customUser.getClass());
        return "You are logged as " + customUser;
    }
}
