package com.cyship.sponsor.repository;

import com.cyship.sponsor.model.Award;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AwardRepository extends JpaRepository<Award,String> {

    Optional <Award> findById(String awardID);

    Award save(Award award);

}
