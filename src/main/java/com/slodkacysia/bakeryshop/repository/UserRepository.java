package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
    User findByUserName(String userName);
    List<User> getAllBy();
    User findUserByEmail(String email);
    User findUserById(Long id);


}
