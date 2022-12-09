package com.cyship.sponsor.controller;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.service.AwardService;
import com.cyship.wall.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
