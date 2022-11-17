package com.cyship.user.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Cyclist {
    @Id
    String userId;
    @Column(nullable = false)
    String userEmail;
    String firstName;
    String lastName;
    String password;
    @OneToOne(fetch = FetchType.LAZY)
    Profile profile;
    @OneToMany(fetch = FetchType.LAZY)
    List<Cyclist> friends;
    @OneToMany(fetch = FetchType.LAZY)
    List<Cyclist> locks;
    @OneToMany(fetch = FetchType.LAZY)
    List<Cyclist> favorites;
}
