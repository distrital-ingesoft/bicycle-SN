package com.cyship.route.controller;

<<<<<<< HEAD
=======
import com.cyship.route.model.Coordinate;
>>>>>>> 397635c64afeeaad07882ad8aeddef9d51bade65
import com.cyship.route.model.Route;
import com.cyship.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 397635c64afeeaad07882ad8aeddef9d51bade65
@RestController
public class RouteController {
    @Autowired
    RouteService service;

<<<<<<< HEAD
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
=======
    @PostMapping(value = "/route", consumes = {"application/json"})
    Route addRoute(@RequestBody Route route){
        return service.addRoute(route);
    }

    @GetMapping(value = "/route/{routeId}", consumes = {"application/json"})
    Route addRoute(@PathVariable Integer routeId){
        return service.getRoute(routeId);
    }

    @PostMapping(value = "/route/{routeId}/coordinate", consumes = {"application/json"})
    Route addCoordinate(@PathVariable Integer routeId, @RequestBody Coordinate coordinate){
        return service.addCordinate(routeId, coordinate);
>>>>>>> 397635c64afeeaad07882ad8aeddef9d51bade65
    }
}
