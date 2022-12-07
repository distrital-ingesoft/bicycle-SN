package com.cyship.wall.controller;

import com.cyship.user.model.Profile;
import com.cyship.user.model.User;
import com.cyship.wall.model.Post;
import com.cyship.wall.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    PostService service;

    @PostMapping(value = "/post/{userId}", consumes = {"application/json"})
    Post createPost(@RequestBody Post post, @PathVariable String userId){
        System.out.println("ENTRO");
        try {
            return service.createPost(userId, post.getMessage(), post.getTitle());
        }catch(Exception e){
            return null;
        }
    }
    @GetMapping("/posts")
    List<String> getPosts(){
        try {
            return service.getAll().stream().map(post -> post.getMessage()).collect(Collectors.toList());
        }catch(Exception e){
            return null;
        }
    }

}
