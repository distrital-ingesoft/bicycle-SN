package com.cyship.user.repository;

import com.cyship.user.model.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist,String> {
    Optional<Cyclist> findByEmail(String userEmail);
    Optional<Cyclist> findById(String userId);

    Cyclist save(Cyclist cyclist);

}
