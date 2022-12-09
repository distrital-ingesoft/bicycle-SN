package com.cyship.sponsor.service;

import com.cyship.sponsor.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AwardService {
    @Autowired
    AwardRepository repository;



}
