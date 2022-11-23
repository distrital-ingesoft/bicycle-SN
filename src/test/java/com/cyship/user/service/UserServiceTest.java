package com.cyship.user.service;

import com.cyship.user.model.User;
import com.cyship.user.model.Profile;
import com.cyship.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class UserServiceTests {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService service;

    User getUserMock(String name, String email){
        User user = new User();
        user.setUserId(name);
        user.setEmail(email);
        return user;
    }


    @Test
    void createAccountTest() throws Exception{
        User user = getUserMock("usuario1","usuario1@gmail.com");
        service.createAccount(user);

        Optional<User> op = repository.findById("usuario1");
        assert(!op.isEmpty());
        assert(op.get()!=user);
    }
    @Test
    void updateProfileTest() throws Exception{
        final String FIRST_NAME = "first name";
        User user = getUserMock("usuario2","usuario2@gmail.com");
        service.createAccount(user);
        Profile p = new Profile();
        p.setFirstName(FIRST_NAME);
            service.updateProfile(user.getUserId(), p);
        Optional<User> op = repository.findById(user.getUserId());
        assert(!op.isEmpty());
        Profile profile = op.get().getProfile();
        assert(profile.getFirstName().equals(FIRST_NAME));
    }

    @Test
    void findProfilesTest() throws Exception{
        assert(false);//No probado aún
    }


    @Test
    void addFriendTest() throws Exception{
        assert(false);//No probado aún
    }

}
