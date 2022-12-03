package com.cyship.user.controller;

import com.cyship.user.model.User;
import com.cyship.user.model.Profile;
import com.cyship.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    User createAccount(@RequestBody User user){
        try {
            return service.createAccount(user);
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/user/{userId}/profile")
    Profile updateProfile(@RequestBody Profile profile, @PathVariable String userId){
        try {
            return service.updateProfile(userId, profile);
        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/users")
    List<User> getUsers(){
        return service.getAll();
    }

    @GetMapping("/user")
    List<String> consultProfile(@RequestParam String keywords){
        try {
           return service.findProfiles(keywords);

        }catch(Exception e){
            return null;
        }
    }

/*
    @GetMapping("/users")
    List<Profile> consultProfiles(@PathVariable String keywords){
        try {
            return service.findProfiles(keywords);
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/user/{userId}/profile/follow/{targetUserId}")
    void follow(@PathVariable String userId,  @PathVariable String targetUserId){
        try {
            service.follow(userId, targetUserId);
        }catch(Exception e){
            return ;
        }
    }

    @PostMapping("/user/{userId}/profile/add/{targetUserId}")
    void addFriend(@PathVariable String userId,  @PathVariable String targetUserId){
        try {
            service.addFriend(userId, targetUserId);
        }catch(Exception e){
            return ;
        }
    }
    @PostMapping("/user/{userId}/profile/add/{targetUserId}")
    void acceptFriend(@PathVariable String userId,  @PathVariable String targetUserId){
        try {
            service.addFriend(userId, targetUserId);
        }catch(Exception e){
            return ;
        }
    }

 */
}
