package com.cyship.user.controller;

import com.cyship.user.dto.ProfileRelationship;
import com.cyship.user.exception.ResourceNotFoundException;
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
                    service.getAll().stream().map(User::getUserId).collect(Collectors.toList()):
                    service.findProfiles(keyword);
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/user/{userId}/profile")
    Profile updateProfile(@RequestBody Profile profile, @PathVariable String userId) throws ResourceNotFoundException {
           return service.updateProfile(userId, profile);
    }

    @GetMapping("/user/{userId}/profile")
    Profile consultProfile(@PathVariable String userId) throws ResourceNotFoundException {
        return service.getProfile(userId);
    }

    @PostMapping("/user/{userId}/profile/follow/{targetUserId}")
    void follow(@PathVariable String userId,  @PathVariable String targetUserId) throws ResourceNotFoundException {
        service.follow(userId, targetUserId);
    }
    @PostMapping("/friendship")
    void addFriend(@RequestBody ProfileRelationship relationship) throws ResourceNotFoundException {
        service.requestFriendship(relationship.getSourceAccount(), relationship.getTargetAccount());
    }
    @PutMapping("/friendship")
    void acceptFriend(@RequestBody ProfileRelationship relationship) throws ResourceNotFoundException {
        service.acceptFriendship(relationship.getSourceAccount(), relationship.getTargetAccount());
    }
}