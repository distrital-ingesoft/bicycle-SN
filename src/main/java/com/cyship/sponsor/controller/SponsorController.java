package com.cyship.sponsor.controller;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.model.Sponsor;
import com.cyship.sponsor.service.AwardService;
import com.cyship.sponsor.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SponsorController {
    @Autowired
    SponsorService service;

    @PostMapping(value = "/sponsor/{userId}")
    Sponsor createSponsor(@RequestBody Sponsor sponsor, @PathVariable String userId){
        try {
            return service.createSponsor(userId, sponsor);
        }catch(Exception e){
            return null;
        }
    }
    @GetMapping(value = "/sponsor")
    List<Sponsor> showSponsors(@RequestParam(required = false) String keyword){
        try {
            return(keyword == null || keyword .length()==0)?
                    service.getAll():
                    service.findSponsors(keyword);
        }
        catch (Exception e){
            return null;
        }
    }
}
