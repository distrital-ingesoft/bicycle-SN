package com.cyship.sponsor.controller;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.service.AwardService;
import com.cyship.wall.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AwardController {
    @Autowired
    AwardService service;

    @PostMapping(value = "/award/{sponsorId}")
    Award createAward(@RequestBody Award award, @PathVariable String sponsorId){
        try {
            return service.createAward(sponsorId, award);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    @GetMapping(value = "/award")
    List<Award> listAwards(@RequestParam(required = false) String keyword){
        try {
            return(keyword == null || keyword .length()==0)?
                    service.getAll():
                    service.findAwards(keyword);
        }
        catch (Exception e){
            return null;
        }
    }
    @PutMapping(value = "/award/{awardId}")
    Award updateAward(@PathVariable String awardId, @RequestBody Award newAward) throws Exception {
        return service.updateAward(awardId, newAward);
    }
    @DeleteMapping(value = "/award/{awardId}")
    boolean deleteAward(@PathVariable String awardId) throws Exception {
        return service.deleteAward(awardId);
    }

    @PutMapping(value = "/claimAward/{userId}/{awardId}")
    Award claimAward(@PathVariable String userId, @PathVariable String awardId) throws Exception {
        return service.claimAward(userId,awardId);
    }
}
