package com.cyship.route.repository;

import com.cyship.route.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository <Route, Integer> {

    Optional<Route> findById(Route routeId);

    Route save(Route route);

}
