package com.example.autherization_authentication.controller;

import com.example.autherization_authentication.entity.User;
import com.example.autherization_authentication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/start")
    public String start(){
        return "Welcome";
    }
    @RequestMapping("/test")
    public String test(){
        return "Test";
    }

//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
    @PostMapping("/adduser")
    public User add(@RequestBody User user){
        return userService.add(user);
    }
    @GetMapping("/userName")
  //  @PreAuthorize("hasAuthority('ROLE_USER')")
    public User getUser(@PathVariable String username){
        return userService.getUser(username);
    }

    @GetMapping("/allUsers")
 //   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> users(){
        return userService.users();
    }

    @PutMapping("/update/{userid}")

    public String updateUser(@PathVariable int userid ,@RequestBody User user){
       userService.updateUser(userid,user);
       return "user updated";
    }

    @DeleteMapping("/delete/{userid}")

    public String deleteUser(@PathVariable int userid){
        userService.userDelete(userid);
        return "";
    }
}
