package com.cyship.user.repository;

import com.cyship.user.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepositoryCustom {

    public List<Profile> findByKeyword(String Keyword);

}
