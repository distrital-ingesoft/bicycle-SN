package com.cyship.sponsor.model;

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
public class Award {
    @Id
    @GeneratedValue
    String id;
    Integer type;
    Integer quantity;
    String description;
}
