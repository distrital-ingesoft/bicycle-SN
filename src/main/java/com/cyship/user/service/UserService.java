package com.cyship.user.service;

import com.cyship.user.model.Friendship;
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
        User user = getUser(userId);
        user.setProfile(profile);
        repository.save(user);
        profile = user.getProfile();
        return profile;

    }

    private User getUser(String userId) throws Exception {
        Optional<User> op = repository.findById(userId);
        if(op.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        User user = op.get();
        return user;
    }

    public Profile getProfile(String userId) throws Exception {
        User user = getUser(userId);
        return user.getProfile();
    }

    public List<String> findProfiles(String keywords) {
        return profileRepository.findByKeyword(keywords);
    }


    public void follow(String userId, String targetUserId) throws Exception {
        User user = getUser(userId);
        User targetUser = getUser(targetUserId);
        if(userIsNotLocked(userId, targetUser)){
            user.getFavorites().add(targetUser);
            repository.save(user);
        }
    }

    public void requestFriendship(String userId, String targetUserId) throws Exception {
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

    private static Optional<Friendship> getTargetUserPreviousFriendshipRequest(User userId, User targetUser) {
        Optional<Friendship> opt = targetUser.
                getRequestedFriendships().
                stream().
                filter(friendship -> friendship.getRecipient().getUserId().equals(userId)).
                findAny();
        return opt;
    }

    private static boolean userIsNotLocked(String userId, User targetUser) {
        return targetUser.getLocks().stream().noneMatch(us -> us.getUserId().equals(userId));
    }


    public List<User> getAll() {
        return repository.findAll();
    }


    public void acceptFriendship(String userId, String targetUserId) throws Exception {
        User user = getUser(userId);
        User targetUser = getUser(targetUserId);
        Optional<Friendship> targetUserRequest = getTargetUserPreviousFriendshipRequest(user, targetUser);
        if(!targetUserRequest.isEmpty()){
            throw new Exception("No se encontró solicitud previa de amistad");
        }
        targetUserRequest.get().setAccepted(true);
        repository.save(user);
    }
}

