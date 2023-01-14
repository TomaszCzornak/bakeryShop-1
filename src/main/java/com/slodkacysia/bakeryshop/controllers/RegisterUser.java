package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Buyer;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.RoleRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import com.slodkacysia.bakeryshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class RegisterUser {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RegisterUser(UserRepository userRepository, CartRepository cartRepository, RoleRepository roleRepository, UserServiceImpl userServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.roleRepository = roleRepository;
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping("/register")
    public String registerUser(Model model) {


        Buyer buyer = new Buyer();
        model.addAttribute("buyer", buyer);

        return "registerUser";
    }
    @PostMapping("/register")
    public String addUser(@Valid@ ModelAttribute Buyer buyer, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "registerUser";
        }else{
            List<Buyer> buyerList = userRepository.getAllBy();

            for (Buyer buyerInList : buyerList) {

                if (buyerInList.getEmail().equals(buyer.getEmail())) {
                    model.addAttribute("emailMsg","Taki email już istnieje");
                    return "registerUser";
                }
                if (buyerInList.getUserName().equals(buyer.getUserName())) {
                    model.addAttribute("usernameMsg","Taki użytkownik już jest zarejestrowany");
                    return "registerUser";
                }


            }
            userRepository.save(buyer);
            userServiceImpl.saveBuyer(buyer);
            return "redirect:/login";
        }
    }
}