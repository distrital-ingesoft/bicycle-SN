package com.cyship.user.repository;

import com.cyship.user.model.User;
import com.cyship.user.model.Friendship;
import com.cyship.wall.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String userEmail);
    Optional<User> findById(String userId000);
    User save(User user);


    @Query(
            "select c.requestedFriendships from User c where c.userId = ?1"
    )
    public List<Friendship> findRequestedFriendships(String userId);

    @Query(
            "select c.receivedFriendships from User c where c.userId = ?1"
    )
    public List<Friendship> findReceivedFriendships(String userId);

    @Query("select c from User c where c.userId = ?1")
    public List<User> findAmigos(String userId);

    @Query(
            "select c.myPosts from User c where c.userId = ?1"
    )
    public List<Post> findPosts(String userId);
}
