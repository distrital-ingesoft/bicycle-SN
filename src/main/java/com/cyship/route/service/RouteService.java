package com.cyship.route.service;

import com.cyship.route.model.Coordinate;
import com.cyship.route.model.Route;
import com.cyship.route.repository.RouteRepository;
import com.cyship.user.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RouteService {
    @Autowired
    RouteRepository repository;
    public Route addRoute(Route route) {
        return repository.save(route);
    }

    public Route getRoute(Integer routeId) {
        return findRoute(routeId);
    }

    public Route addCordinate(Integer routeId, Coordinate coordinate) {
        Route route = findRoute(routeId);
        route.getCoordinates().add(coordinate);
        repository.save(route);
        return route;
    }

    private Route findRoute(Integer routeId) {
        Optional<Route> op = repository.findById(routeId);
        if(op.isEmpty()){
            throw new ResourceNotFoundException("Ruta no encontrada");
        }
        return op.get();
    }
}
