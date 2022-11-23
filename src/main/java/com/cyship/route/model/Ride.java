package com.cyship.route.model;

import com.cyship.route.model.Route;
import com.cyship.sponsor.model.Award;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Ride {
    @Id
    @GeneratedValue
    Integer id;

    Date hour;
    Date date;
    Integer type;

    @OneToMany(fetch = FetchType.LAZY)
    List<Award> awards;

    @ManyToOne(fetch = FetchType.LAZY)
    Route route;
}
