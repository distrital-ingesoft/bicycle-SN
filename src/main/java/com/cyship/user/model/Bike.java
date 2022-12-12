package com.cyship.user.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Bike {
    @Id
    String id;
    Integer type;
    String color;
    String image;
    String characteristics;
    Integer state;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
