package com.cyship.sponsor.service;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.model.Sponsor;
import com.cyship.sponsor.repository.AwardRepository;
import com.cyship.sponsor.repository.SponsorRepository;
import com.cyship.sponsor.repository.SponsorRepositoryImpl;
import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import com.cyship.wall.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SponsorService {
    @Autowired
    SponsorRepository repository;

    @Autowired
    SponsorRepositoryImpl repositoryImpl;

    @Autowired
    UserRepository userRepository;

    public Sponsor createSponsor(String userId, Sponsor sponsor) throws Exception {

        if(userRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        User temp = userRepository.findById(userId).get();
        sponsor.setUser(temp);
        long cant = (repository.count() + 1);
        while (!repository.findById(String.valueOf(cant)).isEmpty()){
            cant = cant + 1;
        }
        sponsor.setId(String.valueOf(cant));

        repository.save(sponsor);
        return sponsor;
    }

    public List<Sponsor> getAll() {
        return repository.findAll();
    }


    public List<Sponsor> findSponsors(String keyword) throws Exception {
        List<String> users = repositoryImpl.findByKeyword(keyword);
        List<Sponsor> sponsors = new ArrayList<>();
        System.out.println("Sponsor recuperados" + users.size());
        for(int x = 0 ; x<users.size(); x++){
            sponsors.add(getSponsor(users.get(x)));
        }
        return sponsors;
    }
    public Sponsor getSponsor(String sponsorId) throws Exception{
        Optional<Sponsor> op = repository.findById(sponsorId);
        if(op.isEmpty()){
            throw new Exception("El sponsor no se encontro");
        }
        return op.get();
    }

    public Sponsor updateSponsor(String userId, Sponsor newSponsor) throws Exception {
        Sponsor oldSponsor = getSponsor(userId);

        if (newSponsor.getName() != null){
            oldSponsor.setName(newSponsor.getName());
        }
        if (newSponsor.getType() != null){
            oldSponsor.setType(newSponsor.getType());
        }
        repository.save(oldSponsor);
        return oldSponsor;
    }

    public boolean deleteSponsor(String sponsorId) throws Exception {
        try {
            Sponsor sponsor = getSponsor(sponsorId);
            repository.delete(sponsor);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
