package com.cyship.sponsor.controller;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.model.Sponsor;
import com.cyship.sponsor.service.AwardService;
import com.cyship.sponsor.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SponsorController {
    @Autowired
    SponsorService service;

    @PostMapping(value = "/sponsor/{userId}")
    Award createSponsor(@RequestBody Sponsor sponsor, @PathVariable String userId){
        try {
            return service.createSponsor(userId, sponsor.getName());
        }catch(Exception e){
            return null;
        }
    }
}
