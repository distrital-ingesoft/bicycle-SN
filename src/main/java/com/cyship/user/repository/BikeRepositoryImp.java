package com.cyship.user.repository;

import com.cyship.user.model.Bike;
import com.cyship.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class BikeRepositoryImp {

    @Autowired
    EntityManager em;


    public List<String> findByKeyword(String keyword) {
        try {
            String query = "SELECT b.id "+
                    "FROM User u JOIN Bike b ON b.user = u " +
                    "WHERE u.userId like ?1 OR u.email like ?1 OR "+
                    "(b.color like ?1 or b.characteristics like ?1) ";
            Query queryJpa =  em.createQuery(query);
            queryJpa.setParameter(1, "%" + keyword + "%");
            return queryJpa.getResultList();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

}