package com.cyship.wall.controller;

import com.cyship.user.model.Profile;
import com.cyship.user.model.User;
import com.cyship.wall.model.Post;
import com.cyship.wall.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    PostService service;

    /**
     * Metodo para crear post
     * @param post Detalles del post a crear
     * @param userId Id del usuario que lo creará
     * @return
     */
    @PostMapping(value = "/post/{userId}", consumes = {"application/json"})
    Post createPost(@RequestBody Post post, @PathVariable String userId){
        try {
            return service.createPost(userId, post);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Metodo para ver el listado de posts, ya sea con una Keyword para filtrar o mostrando todos en caso contrario
     * @param keyword Palabra por la cual se filtrará
     * @return
     */

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
    @GetMapping("post/{postId}")
    Post getPost(@PathVariable String postId) throws Exception {
        return service.getPost(postId);
    }

    @PutMapping("/post/{postId}")
    Post updatePost(@PathVariable String postId, @RequestBody Post postDetail) throws Exception {
        return service.updatePost(postId, postDetail);
    }

    @DeleteMapping("/post/{postId}")
    Void deletePost(@PathVariable String postId) throws Exception {
        service.deletePost(postId);
        return null;
    }
}
