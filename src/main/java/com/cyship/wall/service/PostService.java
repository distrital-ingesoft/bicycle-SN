package com.cyship.wall.service;

import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import com.cyship.wall.model.Post;
import com.cyship.wall.repository.PostRepository;
import com.cyship.wall.repository.PostRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository usrRepository;

    @Autowired
    PostRepositoryImpl repositoryImpl;

    /**
     * Creacion del post
     * @param userId Id del usuario dueño del post
     * @param post Datos del post
     * @return
     * @throws Exception
     */
    public Post createPost(String userId, Post post) throws Exception{
        // Se comprueba que exista el usuario
        if(usrRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        //Se comprueba que el post tenga titulo y contenido
        if(post.getMessage() == null || post.getTitle() == null){
            throw new Exception("Post sin mensaje");
        }
        //Se recupera el usuario
        User temp = usrRepository.findById(userId).get();
        //Se mira cuantos post existen para asignar el id.
        long cant = repository.count() + 1;
        //Cuando se borra un post, se altera un poco el orden de los IDS, por lo que se comprueba que el Id no esté siendo usado
        while (!repository.findById(String.valueOf(cant)).isEmpty()){
            cant = cant + 1;
        }
        post.setUser(temp);
        post.setPostId(String.valueOf(cant));
        post.setHour(LocalTime.now());
        post.setDate(Date.valueOf(LocalDate.now()));
        try {
            repository.save(post);
        }catch (Exception e){
        }
        return post;
    }

    /**
     * Método para recuperar un post especifico
     * @param postId Id del post a buscar
     * @return
     * @throws Exception
     */
    public Post getPost(String postId) throws Exception{
        Optional<Post> op = repository.findById(postId);
        if(op.isEmpty()){
            throw new Exception("El post no se encuentro");
        }
        Post post = op.get();
        return post;
    }

    public List<Post> getAll() {
        return repository.findAll();
    }

    /**
     * Metodo que entrega todos los post de un usuario en especifico
     * @param userId Id del usuario del cual se quieren los post
     * @return
     * @throws Exception
     */
    public List<Post> getUserPost(String userId) throws Exception{
        if(usrRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        User temp = usrRepository.findById(userId).get();
        return repository.findByUser(temp);
    }

    /**
     * Buscar post por una llave particular (en su titulo o contenido)
     * @param keyword
     * @return
     * @throws Exception
     */
    public List<Post> findPosts(String keyword) throws Exception {
        List<String> users = repositoryImpl.findByKeyword(keyword);
        List<Post> posts = new ArrayList<>();
        for(int x = 0 ; x<users.size(); x++){
            posts.add(getPost(users.get(x)));
        }

        return posts;
    }

    /**
     * Metodo para editar el post
     * @param postId Id del post a editar
     * @param postDetail Informacion nueva que irá al post
     * @return
     * @throws Exception
     */
    public Post updatePost(String postId, Post postDetail) throws Exception {
        Post oldPost = getPost(postId);
        if (postDetail.getTitle() != null){
            oldPost.setTitle(postDetail.getTitle());
        }
        if (postDetail.getMessage() != null){
            oldPost.setMessage(postDetail.getMessage());
        }
        repository.save(oldPost);
        return oldPost;

    }

    /**
     * Metodo para eliminar un post
     * @param postId Id del post a borrar
     * @throws Exception
     */
    public void deletePost(String postId) throws Exception {
        Post post = getPost(postId);
        repository.delete(post);
    }
}
