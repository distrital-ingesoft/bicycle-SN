package com.cyship.user.repository;

import com.cyship.user.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepositoryCustom {
/*
        @Query(
                "select u.id from User u where u.name like ?1 or u.correo like ?1"
        )
        public List<String> findByKeyword2(String Keyword);

        public default List<String> findByKeyword(String Keyword) {
            return findByKeyword2("%" + Keyword +  "%");
        }

 */
        public List<String> findByKeyword(String Keyword) ;
}
