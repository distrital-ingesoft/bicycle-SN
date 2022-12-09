package com.cyship.user.repository;

import com.cyship.user.model.Friendship;
import com.cyship.user.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@Component
public class ProfileRepositoryImpl   {
//    @Autowired
//    @Lazy
//    ProfileRepository profileRepository;

    @Autowired
    EntityManager em;

    public List<String> findByKeyword(String keyword) {
        try {
            String query = "SELECT DISTINCT u.id "+
                    "FROM User u, Profile p "+
                    "WHERE u.userId like ?1 OR u.email like ?1 OR "+
                    "(u.profile = p and  (p.firstName like ?1 or p.lastName like ?1 )) ";
            Query queryJpa =  em.createQuery(query);
            queryJpa.setParameter(1, "%" + keyword + "%");
            return queryJpa.getResultList();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }


/*    public List<String> findByKeyword(String keyword) {
        String jpql = "SELECT DISTINCT u.id " +
                "FROM User u " +
                "JOIN u.profile p " +
                "WHERE u.userId LIKE :keyword " +
                "   OR u.email LIKE :keyword " +
                "   OR (p.firstName LIKE :keyword " +
                "       OR p.lastName LIKE :keyword " +
                "       OR :keyword MEMBER OF p.tags)";

        Query query = em.createQuery(jpql);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }
*/
}
