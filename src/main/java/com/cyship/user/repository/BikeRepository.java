package com.cyship.user.repository;

import com.cyship.user.model.Bike;
import com.cyship.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BikeRepository extends JpaRepository<Bike, String> {

    Bike save(Bike bike);

    Optional<Bike> findById(String bikeId);

    List<Bike> findByUser (User usr);
}
