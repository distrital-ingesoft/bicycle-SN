package com.cyship.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class User {
    @Id
    String userId;
    @Column(nullable = false)
    String email;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    Profile profile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    List<User> locks;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    List<User> favorites;

    @JsonIgnore
    @OneToMany(
            mappedBy = "requester",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Friendship> requestedFriendships = new ArrayList<>();

    @JsonIgnore
    @OneToMany (
            mappedBy = "recipient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Friendship> receivedFriendships = new ArrayList<>();

}
