package com.cyship.user.repository;

import com.cyship.user.model.Friendship;
import com.cyship.user.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.security.Key;
import java.util.List;

@Component
public class ProfileRepositoryImpl  {
//    @Autowired
//    @Lazy
//    ProfileRepository profileRepository;

    @Autowired
    EntityManager em;

    public List<String> findByKeyword(String Keyword) {
        String query = "select u.id from User u where u.name like ?1";

        Query queryJpa = em.createQuery(query);
        queryJpa.setParameter(1, "%" + Keyword + "%");
        return queryJpa.getResultList();
    }
}
