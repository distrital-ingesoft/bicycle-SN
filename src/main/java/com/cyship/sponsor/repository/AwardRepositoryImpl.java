package com.cyship.sponsor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class AwardRepositoryImpl {

    @Autowired
    EntityManager em;


    public List<String> findByKeyword(String keyword) {
        try {
            String query = "SELECT a.id "+
                    "FROM Sponsor s JOIN Award a ON a.sponsor = s " +
                    "WHERE s.name like ?1 OR s.type like ?1 OR "+
                    "(a.description like ?1 or a.type like ?1) ";
            Query queryJpa =  em.createQuery(query);
            queryJpa.setParameter(1, "%" + keyword + "%");
            return queryJpa.getResultList();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

}
