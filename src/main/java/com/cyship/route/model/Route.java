package com.cyship.route.model;

import com.cyship.sponsor.model.Sponsor;
import com.cyship.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Route {
    @Id
    String id;
    @OneToMany(fetch = FetchType.LAZY)
    List<Coordinate> coordinates;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
