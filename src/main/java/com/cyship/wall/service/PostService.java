package com.cyship.wall.service;

import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import com.cyship.wall.model.Post;
import com.cyship.wall.repository.PostRepository;
import com.cyship.wall.repository.PostRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Post createPost(String userId, Post post) throws Exception{
        if(usrRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        if(post.getMessage() == null || post.getTitle() == null){
            throw new Exception("Post sin mensaje");
        }
        User temp = usrRepository.findById(userId).get();
        long cant = repository.count() + 1;
        post.setUser(temp);
        post.setPostId(String.valueOf(cant));
        try {
            repository.save(post);
        }catch (Exception e){
            System.out.println(e);
        }
        return post;
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

    public List<Post> getUserPost(String userId) throws Exception{
        if(usrRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        User temp = usrRepository.findById(userId).get();
        return repository.findByUser(temp);
    }


    public List<Post> findPosts(String keyword) throws Exception {
        List<String> users = repositoryImpl.findByKeyword(keyword);
        List<Post> posts = new ArrayList<>();
        System.out.println("Post recuperados" + users.size());
        for(int x = 0 ; x<users.size(); x++){
            posts.add(getPost(users.get(x)));
        }

        return posts;
    }
}
