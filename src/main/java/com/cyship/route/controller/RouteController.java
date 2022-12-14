package com.cyship.route.controller;

import com.cyship.route.model.Route;
import com.cyship.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {
    @Autowired
    RouteService service;

    @PostMapping(value = "/route/{userId}")
    Route createRoute(@RequestBody Route route, @PathVariable String userId){
        try {
            return service.createRoute(userId, route);
        }catch(Exception e){
            return null;
        }
    }
    @GetMapping(value = "/route")
    List<Route> showSponsors(@RequestParam(required = false) String keyword){
        try {
            return service.getAll();
        }
        catch (Exception e){
            return null;
        }
    }

    @DeleteMapping(value = "/route/{routeId}")
    Boolean deleteSponsor(@PathVariable String routeId){
        try{
            service.deleteRoute(routeId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
