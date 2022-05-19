package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.Customer;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.CustomerRepository;
import com.slodkacysia.bakeryshop.repository.RoleRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import com.slodkacysia.bakeryshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


@Controller
public class RegisterUser {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RegisterUser(UserRepository userRepository, CartRepository cartRepository, RoleRepository roleRepository, CustomerRepository customerRepository, UserServiceImpl userServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping("/register")
    public String registerUser(Model model) {


        User user = new User();
        model.addAttribute("user", user);

        return "registerUser";
    }
    @PostMapping("/register")
    public String addUser(@Valid@ ModelAttribute User user, BindingResult bindingResult, Model model){

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
            Customer customer = new Customer();
            customer.setUserId(user.getId());
            customer.setEnabled(1);
            customer.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            System.out.println("hasło customer " + customer.getPassword());
            customer.setUsername(user.getUserName());
            customer.setEmail(user.getEmail());
            customer.setCity(user.getCity());
            customer.setPost_code(user.getPost_code());
            customer.setStreet(user.getStreet());
            customer.setFirst_name(user.getFirst_name());
            customer.setLast_name(user.getLast_name());
            customer.setPhone(user.getPhone());


            customerRepository.save(customer);

            Cart cart = new Cart();
            cart.setUser(user);
            cart.setTotal_amount(BigDecimal.ZERO);

            cartRepository.save(cart);

            user.setCart(cart);
            userRepository.save(user);
            System.out.println("Hasło user " + user.getPassword());


            userServiceImpl.saveUser(user);
            System.out.println("hasło userServiceImpl ");
            return "redirect:/login";
        }
    }
}