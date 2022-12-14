package com.cyship.user.controller;

import com.cyship.user.dto.ProfileRelationship;
import com.cyship.user.model.User;
import com.cyship.user.model.Profile;
import com.cyship.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping(value = "/user", consumes = {"application/json"})
    String createAccount(@RequestBody User user){
        try {
            service.createAccount(user);
            return "Creado el usuario con el Email" + user.getEmail();
        }catch(Exception e){
            return "El usauario no se ha podido crear";
        }
    }

    @GetMapping("/user")
    List<String> getUsers(@RequestParam(required = false) String keyword){
        try {
            return(keyword == null || keyword .length()==0)?
                    service.getAll().stream().map(user -> user.getUserId()).collect(Collectors.toList()):
                    service.findProfiles(keyword);
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

    @GetMapping("/user/{userId}/profile")
    Profile consultProfiles(@PathVariable String userId){
        try {
            return service.getProfile(userId);
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
    @PostMapping("/friendship")
    void addFriend(@RequestBody ProfileRelationship relationship){
        try {
            service.requestFriendship(relationship.getSourceAccount(), relationship.getTargetAccount());
        }catch(Exception e){
            return ;
        }
    }
    @PutMapping("/friendship")
    void acceptFriend(@RequestBody ProfileRelationship relationship){
        try {
            service.acceptFriendship(relationship.getSourceAccount(), relationship.getTargetAccount());
        }catch(Exception e){
            return ;
        }
    }
}