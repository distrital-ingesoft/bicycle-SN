package com.cyship.user.controller;

import com.cyship.user.model.Bike;
import com.cyship.user.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BikeController {
    @Autowired
    BikeService service;

    @PostMapping(value = "/user/{userId}/bike")
    Bike createBike(@RequestBody Bike bike, @PathVariable String userId){
        return service.createBike(bike,userId);
    }

    @GetMapping(value = "/bike")
    List<Bike> showBikes(@RequestParam(required = false) String keyword){
        try {
            return(keyword == null || keyword .length()==0)?
                    service.getAll():
                    service.findBikes(keyword);
        }
        catch (Exception e){
            return null;
        }
    }

}
