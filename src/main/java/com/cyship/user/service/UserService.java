package com.cyship.user.service;

import com.cyship.user.model.User;
import com.cyship.user.model.Profile;
import com.cyship.user.repository.UserRepository;
import com.cyship.user.repository.ProfileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    ProfileRepositoryImpl profileRepository;
    public User createAccount(User user) throws Exception {
        if(!repository.findById(user.getUserId()).isEmpty()){
            throw new Exception("El nombre de usuario ya se encuentra registrado");
        }

        if(!repository.findByEmail(user.getEmail()).isEmpty()){
            throw new Exception("El nombre de usuario ya se encuentra registrado");
        }
        repository.save(user);
        return user;
    }

    public Profile updateProfile(String userId, Profile profile) throws Exception {
        Optional<User> op = repository.findById(userId);
        if(op.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        User user = op.get();
        user.setProfile(profile);
        repository.save(user);
        profile = user.getProfile();
        return profile;

    }

    public List<String> findProfiles(String keywords) {
        return profileRepository.findByKeyword(keywords);
    }


    public void follow(String userId, String targetUserId) throws Exception {
        Optional<User> userOpt = repository.findById(userId);
        if(userOpt.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Optional<User> targetUserOpt = repository.findById(targetUserId);
        if(targetUserOpt.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        User user = userOpt.get();
        User targetUser = targetUserOpt.get();
        //verificar lista de bloqueos
        user.getFavorites().add(targetUser);
        repository.save(user);
    }

    public void addFriend(String userId, String targetUserId) throws Exception {

    }


    public List<User> getAll() {
        return repository.findAll();
    }
}
