package com.cyship.sponsor.service;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.model.Sponsor;
import com.cyship.sponsor.repository.AwardRepository;
import com.cyship.sponsor.repository.AwardRepositoryImpl;
import com.cyship.sponsor.repository.SponsorRepository;
import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AwardService {
    @Autowired
    AwardRepository repository;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    AwardRepositoryImpl repositoryImp;
    @Autowired
    UserRepository userRepository;

    public Award createAward(String sponsorId, Award award) throws Exception {
        if(sponsorRepository.findById(sponsorId).isEmpty()) {
            throw new Exception("Sponsor no encontrado");
        }
        Sponsor sp = sponsorRepository.findById(sponsorId).get();
        award.setSponsor(sp);
        long cant = repository.count()+1;
        while (!repository.findById(String.valueOf(cant)).isEmpty()){
            cant = cant + 1;
        }
        award.setId(String.valueOf(cant));
        try{
            repository.save(award);
        }catch (Exception e){
            System.out.println(e);
        }
        return award;
    }

    public List<Award> getAll() {
        return repository.findAll();
    }

    public Award getAward(String awardId) throws Exception {
        Optional<Award> op = repository.findById(awardId);
        if(op.isEmpty()){
            throw new Exception("El premio no se encontro");
        }
        return op.get();
    }

    public List<Award> findAwards(String keyword) throws Exception {
        List<String> fList = repositoryImp.findByKeyword(keyword);
        List<Award> awards = new ArrayList<>();
        System.out.println("Sponsor recuperados" + fList.size());
        for(int x = 0 ; x<fList.size(); x++){
            awards.add(getAward(fList.get(x)));
        }
        return awards;
    }

    public Award updateAward(String awardId, Award newAward) throws Exception {
        Award oldAward = getAward(awardId);
        if(newAward.getType() != null){
            oldAward.setType(newAward.getType());
        }
        if(newAward.getDescription() != null){
            oldAward.setDescription(newAward.getDescription());
        }
        if(newAward.getQuantity() != null){
            oldAward.setQuantity(newAward.getQuantity());
        }
        repository.save(oldAward);
        return oldAward;
    }

    public boolean deleteAward(String awardId) throws Exception {
        try {
            Award award = getAward(awardId);
            repository.delete(award);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Award claimAward(String userId, String awardId) throws Exception {
        Optional<User> usrOp = userRepository.findById(userId);
        Award op = getAward(awardId);
        if(usrOp.isEmpty()){
            throw new Exception("El usuario no se encontro");
        }
        if(op.getQuantity()<1){
            throw new Exception("Cantidad del premio menor a 0");
        }
        op.setQuantity(op.getQuantity() - 1);
        repository.save(op);
        return op;

    }
}
