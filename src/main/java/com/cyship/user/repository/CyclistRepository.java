package com.cyship.user.repository;

import com.cyship.user.model.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist,String> {

    // genera automáticamente estas busquedas
    Optional<Cyclist> findByuserEmail(String userEmail);
    Optional<Cyclist> findByuserId(String userId);

    // S save(Cyclist cyclist);

}
