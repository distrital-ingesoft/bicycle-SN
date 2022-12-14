package com.cyship.route.controller;

import com.cyship.route.model.Ride;
import com.cyship.route.model.Route;
import com.cyship.route.service.RideService;
import com.cyship.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RideController {
    @Autowired
    RideService service;

    @PostMapping(value = "/ride", consumes = {"application/json"})
    Ride addRoute(@RequestBody Ride ride){
        return service.addRide(ride);
    }

    @GetMapping(value = "/ride/{rideId}", consumes = {"application/json"})
    Ride getRide(@RequestParam Integer rideId){
        return service.getRide(rideId);
    }
}
