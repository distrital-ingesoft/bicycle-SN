package com.cyship.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class Friendship {

    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    User recipient;

    Boolean accepted;

    public Friendship(User requester, User recipient){
        this.requester = requester;
        this. recipient = recipient;
        accepted = false;
    }
}