package com.cyship.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}