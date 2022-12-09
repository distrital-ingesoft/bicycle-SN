package com.cyship.sponsor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class SponsorRepositoryImpl {

    @Autowired
    EntityManager em;


    public List<String> findByKeyword(String keyword) {
        try {
            String query = "SELECT s.id "+
                    "FROM User u JOIN Sponsor s ON s.user = u " +
                    "WHERE u.userId like ?1 OR u.email like ?1 OR "+
                    "(s.name like ?1 or s.type like ?1) ";
            Query queryJpa =  em.createQuery(query);
            queryJpa.setParameter(1, "%" + keyword + "%");
            return queryJpa.getResultList();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

}
