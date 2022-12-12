package com.cyship.user.service;

import com.cyship.sponsor.model.Sponsor;
import com.cyship.user.model.Bike;
import com.cyship.user.model.User;
import com.cyship.user.repository.BikeRepository;
import com.cyship.user.repository.BikeRepositoryImp;
import com.cyship.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;
    @Autowired
    BikeRepositoryImp bikeRepositoryImp;
    @Autowired
    UserRepository userRepository;
    public Bike createBike(Bike bike, String userId) {
        User op = userRepository.findById(userId).get();
        bike.setUser(op);
        long cant = bikeRepository.count() + 1;
        while (!bikeRepository.findById(String.valueOf(cant)).isEmpty()){
            cant = cant + 1;
        }
        bike.setId(String.valueOf(cant));
        bikeRepository.save(bike);
        return bike;
    }

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public List<Bike> findBikes(String keyword) throws Exception {
        List<String> users = bikeRepositoryImp.findByKeyword(keyword);
        List<Bike> bikes = new ArrayList<>();
        for(int x = 0 ; x<users.size(); x++){
            bikes.add(getBike(users.get(x)));
        }
        return bikes;
    }

    private Bike getBike(String s) throws Exception {
        Optional<Bike> op = bikeRepository.findById(s);
        if(op.isEmpty()){
            throw new Exception("El sponsor no se encontro");
        }
        return op.get();
    }
}
