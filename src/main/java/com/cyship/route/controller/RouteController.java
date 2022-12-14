package com.cyship.route.controller;

import com.cyship.route.model.Coordinate;
import com.cyship.route.model.Route;
import com.cyship.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteController {
    @Autowired
    RouteService service;

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
    }
}
