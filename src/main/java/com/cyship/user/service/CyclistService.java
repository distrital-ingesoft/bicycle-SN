package com.cyship.user.service;

import com.cyship.user.model.Cyclist;
import com.cyship.user.repository.CyclistRepository;
import com.cyship.user.model.Profile;
import com.cyship.user.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistService {

    @Autowired
    CyclistRepository repository;
    
    @Autowired
    ProfileRepository profileRepository;
    
    public Cyclist createAccount(Cyclist user) throws Exception {
        if(!repository.findById(user.getUserId()).isEmpty()){
            throw new Exception("El nombre de usuario ya se encuentra registrado");
        }

        if(!repository.findByuserEmail(user.getUserEmail()).isEmpty()){
            throw new Exception("El nombre de usuario ya se encuentra registrado");
        }
        repository.save(user);
        return user;
    }

    public Profile updateProfile(String userId, Profile profile) throws Exception {
        Optional<Cyclist> op = repository.findById(userId);
        if(op.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Cyclist cyclist = op.get();
        cyclist.setProfile(profile);
        repository.save(cyclist);
        profile = cyclist.getProfile();
        return profile;

    }

    public List<Profile> findProfiles(String keywords) {
        return profileRepository.findByKeyword(keywords);
    }

    public void follow(String userId, String targetUserId) throws Exception {
        Optional<Cyclist> userOpt = repository.findById(userId);
        if(userOpt.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Optional<Cyclist> targetUserOpt = repository.findById(targetUserId);
        if(targetUserOpt.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Cyclist cyclist = userOpt.get();
        Cyclist targetCyclist = targetUserOpt.get();
        //verificar lsita de bloqueos
        cyclist.getFavorites().add(targetCyclist);
        repository.save(cyclist);
    }

    public void addFriend(String userId, String targetUserId) throws Exception {

    }
}
