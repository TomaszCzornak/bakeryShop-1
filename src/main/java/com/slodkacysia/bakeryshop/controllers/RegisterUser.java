package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Role;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.RoleRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;


@Controller
public class RegisterUser {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private RegisterUser(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/register")
    public String registerUser(Model model) {


        User user = new User();
        model.addAttribute("user", user);

        return "registerUser";
    }
    @PostMapping("/register")
    public String addUser(@Valid@ ModelAttribute User user, BindingResult bindingResult, Model model){
        System.out.println("drukowanie " +user.getEmail());
        System.out.println("drukowanie " +user.getFirst_name());
        System.out.println("drukowanie " +user.getLast_name());
        if(bindingResult.hasErrors()){
            return "registerUser";
        }else{
            List<User> userList = userRepository.getAllBy();

            for (User userInList : userList) {

                if (userInList.getEmail().equals(user.getEmail())) {
                    model.addAttribute("emailMsg","Taki email już istnieje");
                    return "registerUser";
                }


            }
//            Role role = new Role();
            Role admin_role = roleRepository.findByName("ROLE_ADMIN");
//            role.setEmail(user.getEmail()); do wyrzecnia - setter rola na userze

            user.setRoles(Collections.singleton(admin_role));
            userRepository.save(user);
            return "redirect:/health";
        }
    }
}