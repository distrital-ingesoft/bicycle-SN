package com.cyship.sponsor.service;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.model.Sponsor;
import com.cyship.sponsor.repository.AwardRepository;
import com.cyship.sponsor.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AwardService {
    @Autowired
    AwardRepository repository;
    @Autowired
    SponsorRepository sponsorRepository;

    public Award createAward(String sponsorId, Award award) throws Exception {
        if(sponsorRepository.findById(sponsorId).isEmpty()) {
            throw new Exception("Sponsor no encontrado");
        }
        Sponsor sp = sponsorRepository.findById(sponsorId).get();
        award.setSponsor(sp);
        String x = String.valueOf(repository.count()+1);
        award.setId(x);
        System.out.println("Bien");
        try{
            repository.save(award);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Bien hasta aca");
        return award;
    }
}
