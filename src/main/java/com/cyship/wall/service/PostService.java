package com.cyship.wall.service;

import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import com.cyship.wall.model.Post;
import com.cyship.wall.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository usrRepository;

    public Post createPost(String userId, String message, String tittle) throws Exception{
        if(usrRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        if(message == null){
            throw new Exception("Post sin mensaje");
        }
        User temp = usrRepository.findById(userId).get();
        long cant = repository.count() + 1;
        Post p = new Post(message,temp,tittle, cant);
        try {
            repository.save(p);
        }catch (Exception e){
            System.out.println("");
            System.out.println(e);
        }
        System.out.println("Usuario guardado");
        return p;
    }

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



}
