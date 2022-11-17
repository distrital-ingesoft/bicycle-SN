package com.cyship.user.controller;

import com.cyship.user.model.Cyclist;
import com.cyship.user.model.Profile;
import com.cyship.user.service.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CyclistController {
    @Autowired
    CyclistService service;

    @PostMapping("/user")
    Cyclist createAccount(@RequestBody Cyclist user){
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
    @GetMapping("/user/profile/{keywords}")
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
}
