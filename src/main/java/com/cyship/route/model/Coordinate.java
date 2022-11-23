package com.cyship.route.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Coordinate {
    @Id @GeneratedValue
    Integer id;

    Integer x;
    Integer y;
}
