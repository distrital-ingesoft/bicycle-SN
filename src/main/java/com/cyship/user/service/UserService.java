package com.cyship.user.service;

import com.cyship.user.exception.ResourceAlreadyRegistredException;
import com.cyship.user.model.Friendship;
import com.cyship.user.model.User;
import com.cyship.user.model.Profile;
import com.cyship.user.repository.UserRepository;
import com.cyship.user.repository.ProfileRepositoryImpl;
import com.cyship.user.exception.ResourceNotFoundException;
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
<<<<<<< HEAD
            System.out.println("ID YA REGISTRADO");
            throw new Exception("El nombre de usuario ya se encuentra registrado");
        }

        if(!repository.findByEmail(user.getEmail()).isEmpty()){
            System.out.println("E-MAIL YA REGISTRADO");
            throw new Exception("El nombre de usuario ya se encuentra registrado");
=======
            throw new ResourceAlreadyRegistredException("El nombre de usuario ya se encuentra registrado");
        }

        if(!repository.findByEmail(user.getEmail()).isEmpty()){
            throw new ResourceAlreadyRegistredException("El nombre de usuario ya se encuentra registrado");
>>>>>>> 397635c64afeeaad07882ad8aeddef9d51bade65
        }
        repository.save(user);
        return user;
    }

    public Profile updateProfile(String userId, Profile profile) throws ResourceNotFoundException {
        User user = getUser(userId);
        user.setProfile(profile);
        repository.save(user);
        profile = user.getProfile();
        return profile;

    }

    private User getUser(String userId) throws ResourceNotFoundException {
        Optional<User> op = repository.findById(userId);
        if(op.isEmpty()){
            throw new ResourceNotFoundException("El usuario no se encuentra registrado");
        }
        return op.get();
    }

    public Profile getProfile(String userId) throws ResourceNotFoundException {
        User user = getUser(userId);
        return user.getProfile();
    }

    public List<String> findProfiles(String keywords) {
        return profileRepository.findByKeyword(keywords);
    }


    public void follow(String userId, String targetUserId) throws ResourceNotFoundException {
        User user = getUser(userId);
        User targetUser = getUser(targetUserId);
        if(userIsNotLocked(userId, targetUser)){
            user.getFavorites().add(targetUser);
            repository.save(user);
        }
    }

    public void requestFriendship(String userId, String targetUserId) throws ResourceNotFoundException {
        User user = getUser(userId);
        User targetUser = getUser(targetUserId);
        if(userIsNotLocked(userId, targetUser) && !getUserPreviousFriendshipRequest( user, targetUserId)){
            Optional<Friendship> targetUserRequest = getTargetUserPreviousFriendshipRequest(user, targetUser);
            if(!targetUserRequest.isEmpty()){
                targetUserRequest.get().setAccepted(true);
            }
            else{
                user.getRequestedFriendships().add(new Friendship(user, targetUser));
            }
            repository.save(user);
        }
    }

    private static boolean getUserPreviousFriendshipRequest( User user, String targetUserId) {
        return user.getRequestedFriendships().stream().anyMatch(friendship -> friendship.getRecipient().getUserId().equals(targetUserId));
    }

    private static Optional<Friendship> getTargetUserPreviousFriendshipRequest(User user, User targetUser) {
        return targetUser.
                getRequestedFriendships().
                stream().
                filter(friendship -> friendship.getRecipient().getUserId().equals(user.getUserId())).
                findAny();
    }

    private static boolean userIsNotLocked(String userId, User targetUser) {
        return targetUser.getLocks().stream().noneMatch(us -> us.getUserId().equals(userId));
    }


    public List<User> getAll() {
        return repository.findAll();
    }


    public void acceptFriendship(String userId, String targetUserId) throws ResourceNotFoundException {
        User user = getUser(userId);
        User targetUser = getUser(targetUserId);
        Optional<Friendship> targetUserRequest = getTargetUserPreviousFriendshipRequest(user, targetUser);
        if(!targetUserRequest.isEmpty()){
            throw new ResourceNotFoundException("No se encontró solicitud previa de amistad");
        }
        targetUserRequest.get().setAccepted(true);
        repository.save(user);
    }
}

