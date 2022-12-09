package com.cyship.sponsor.service;

import com.cyship.sponsor.model.Sponsor;
import com.cyship.sponsor.repository.AwardRepository;
import com.cyship.sponsor.repository.SponsorRepository;
import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SponsorService {
    @Autowired
    SponsorRepository repository;

    @Autowired
    UserRepository userRepository;

    Sponsor createSponsor(String userId, String name){

        Sponsor sponsor =

    }

}
