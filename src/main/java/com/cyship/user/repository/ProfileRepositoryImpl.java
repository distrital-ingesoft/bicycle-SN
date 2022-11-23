package com.cyship.user.repository;

import com.cyship.user.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileRepositoryImpl implements ProfileRepositoryCustom, ProfileRepository {
//    @Autowired
//    @Lazy
//    ProfileRepository profileRepository;

    @Override
    public List<Profile> findByKeyword(String Keyword) {
        return null;
    }
}
