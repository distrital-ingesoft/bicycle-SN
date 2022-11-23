package com.cyship.user.model;

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
public class Bike {
    @Id
    @GeneratedValue
    Integer id;
    Integer type;
    Integer color;
    String Image;
    String Characteristics;
    Integer state;
}
