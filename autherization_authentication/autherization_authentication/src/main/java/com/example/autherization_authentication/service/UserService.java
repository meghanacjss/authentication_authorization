package com.example.autherization_authentication.service;

import com.example.autherization_authentication.entity.User;
import com.example.autherization_authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User add(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(String username){
        System.out.println(userRepository.findByName(username));
        return userRepository.findByName(username).get();
    }

    public Optional<User> getUserDetails(String username){
        System.out.println(userRepository.findByName(username));
        return userRepository.findByName(username);
    }

    public List<User> users(){
        return userRepository.findAll();
    }

//    public Void updateUser(User user){
//        userRepository.save(user);
//    }

    public String updateUser(int userid,User user){
        User userupdate  = userRepository.findById(userid).orElse(null);
        if(userupdate != null){
            userupdate.setUsername(user.getUsername());
            userupdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userupdate.setRoles(user.getRoles());
            userRepository.save(userupdate);
        }
        return "user updated";
    }
    public String userDelete(int userid){
        userRepository.deleteById(userid);
        return "user deleted";
    }
}


