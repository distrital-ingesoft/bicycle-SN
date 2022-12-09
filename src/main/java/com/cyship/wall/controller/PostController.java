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
        try {
            return service.createPost(userId, post);
        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/post")
    List<Post> getPosts(@RequestParam(required = false) String keyword){
        try {
            return(keyword == null || keyword .length()==0)?
                    service.getAll():
                    service.findPosts(keyword);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Este metodo muestra los post del usuario.
     * @param userId Id del usuario a mostrar el post
     * @return
     */
    @GetMapping("/wall/{userId}")
    List<Post> getWall(@PathVariable String userId){
        try {
            return service.getUserPost(userId);
        }catch(Exception e){
            return null;
        }
    }


}
