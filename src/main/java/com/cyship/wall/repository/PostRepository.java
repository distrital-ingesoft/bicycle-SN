package com.cyship.wall.repository;

import com.cyship.user.model.User;
import com.cyship.wall.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    Optional<Post> findById(String postId);

    Post save(Post post);

    List<Post> findByUser(User usr);
}
