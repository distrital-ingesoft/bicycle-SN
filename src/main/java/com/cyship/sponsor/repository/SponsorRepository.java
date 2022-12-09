package com.cyship.sponsor.repository;

import com.cyship.sponsor.model.Award;
import com.cyship.sponsor.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor,String> {

    Optional <Sponsor> findById(String sponsorId);

    Sponsor save(Sponsor sponsor);

}
