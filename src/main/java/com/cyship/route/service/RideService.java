package com.cyship.route.service;

import com.cyship.route.model.Ride;
import com.cyship.route.model.Route;
import com.cyship.route.repository.RideRepository;
import com.cyship.user.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RideService {
    @Autowired
    RideRepository repository;
    public Ride addRide(Ride ride) {
        return repository.save(ride);
    }

    public Ride getRide(Integer rideId) {
        return find(rideId);
    }

    private Ride find(Integer rideId) {
        Optional<Ride> op= repository.findById(rideId);
        if(op.isEmpty()){
            throw new ResourceNotFoundException("No se encontŕo el evento");
        }
        return op.get();
    }
}
