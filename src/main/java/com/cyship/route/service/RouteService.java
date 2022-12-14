package com.cyship.route.service;

<<<<<<< HEAD
import com.cyship.route.model.Route;
import com.cyship.route.repository.RouteRepository;
import com.cyship.sponsor.model.Sponsor;
import com.cyship.user.model.User;
import com.cyship.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RouteService {
    @Autowired
    RouteRepository repository;

    @Autowired
    UserRepository userRepository;

    public Route createRoute(String userId, Route route) throws Exception {
        if(userRepository.findById(userId).isEmpty()){
            throw new Exception("Usuario no encontrado");
        }
        User temp = userRepository.findById(userId).get();
        route.setUser(temp);
        long cant = (repository.count() + 1);
        while (!repository.findById(String.valueOf(cant)).isEmpty()){
            cant = cant + 1;
        }
        route.setId(String.valueOf(cant));

=======
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
>>>>>>> 397635c64afeeaad07882ad8aeddef9d51bade65
        repository.save(route);
        return route;
    }

<<<<<<< HEAD
    public List<Route> getAll() {
        return repository.findAll();
    }


    public Route getSponsor(String routeId) throws Exception{
        Optional<Route> op = repository.findById(routeId);
        if(op.isEmpty()){
            throw new Exception("El sponsor no se encontro");
        }
        return op.get();
    }

    public boolean deleteRoute(String routeId) throws Exception {
        try {
            Route route = getSponsor(routeId);
            repository.delete(route);
            return true;
        }catch (Exception e){
            return false;
        }

    }


=======
    private Route findRoute(Integer routeId) {
        Optional<Route> op = repository.findById(routeId);
        if(op.isEmpty()){
            throw new ResourceNotFoundException("Ruta no encontrada");
        }
        return op.get();
    }
>>>>>>> 397635c64afeeaad07882ad8aeddef9d51bade65
}
