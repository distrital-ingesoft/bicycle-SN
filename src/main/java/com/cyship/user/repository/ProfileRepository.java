package com.cyship.user.repository;

import com.cyship.user.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository {
    List<Profile> findByKeyword(String Keyword);
}
