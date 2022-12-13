package com.cyship.wall.controller;


import com.cyship.wall.model.Post;
import com.cyship.wall.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService service;

    /**
     * Metodo para crear post
     * @param post Detalles del post a crear
     * @param userId Id del usuario que lo creará
     * @return Retorna el post ya creado como confirmación
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
     * @return Retorna una lista de post
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
     * Este metodo muestra los post de un usuario en especifico.
     * @param userId Id del usuario a mostrar el post
     * @return Retorna una lista de post de un usuario especifico.
     */
    @GetMapping("/wall/{userId}")
    List<Post> getWall(@PathVariable String userId){
        try {
            return service.getUserPost(userId);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Retorna un post en especifico filtrado por su ID
     * @param postId Id del post a buscar
     * @return
     * @throws Exception
     */
    @GetMapping("post/{postId}")
    Post getPost(@PathVariable String postId) throws Exception {
        return service.getPost(postId);
    }

    /**
     * Actualiza el post
     * @param postId Post a actualizar
     * @param postDetail Detalles que se actualizarán del post
     * @return Devuelve el post actualizado
     * @throws Exception
     */
    @PutMapping("/post/{postId}")
    Post updatePost(@PathVariable String postId, @RequestBody Post postDetail) throws Exception {
        return service.updatePost(postId, postDetail);
    }

    /**
     * Borrar post dependiendo de su postID
     * @param postId Id del post a borrar
     * @return
     * @throws Exception
     */
    @DeleteMapping("/post/{postId}")
    Void deletePost(@PathVariable String postId) throws Exception {
        service.deletePost(postId);
        return null;
    }
}
