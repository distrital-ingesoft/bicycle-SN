package com.cyship.wall.repository;

import com.cyship.wall.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class PostRepositoryImpl {

    @Autowired
    EntityManager em;


    public List<String> findByKeyword(String keyword) {
        System.out.println("FLAG Method");
        try {
            String query = "SELECT p.postId "+
                    "FROM User u JOIN Post p ON p.user = u " +
                    "WHERE u.userId like ?1 OR u.email like ?1 OR "+
                    "(p.message like ?1 or p.title like ?1) ";
            Query queryJpa =  em.createQuery(query);
            queryJpa.setParameter(1, "%" + keyword + "%");
            System.out.println("Return + " + queryJpa.getResultList().size());
            return queryJpa.getResultList();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

}
