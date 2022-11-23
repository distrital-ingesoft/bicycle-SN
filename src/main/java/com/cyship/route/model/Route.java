package com.cyship.route.model;

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
    @GeneratedValue
    Integer id;

    @OneToMany(fetch = FetchType.LAZY)
    List<Coordinate> coordinates;
}
