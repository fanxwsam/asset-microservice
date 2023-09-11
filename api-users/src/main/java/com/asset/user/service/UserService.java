package com.asset.user.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asset.user.entity.User;
import com.asset.user.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveEmpDetails(User user){
        return userRepository.save(user);
    }

    public User getUser(String key){
        Optional<User> userOptional = userRepository.findById(key);


        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

}
