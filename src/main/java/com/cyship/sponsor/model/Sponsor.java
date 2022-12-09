package com.cyship.sponsor.model;

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

public class Sponsor {
    @Id
    String id;
    String type;
    @OneToOne
    User user;
    String name;
    @OneToMany(fetch = FetchType.LAZY)
    List<Award> awards;

    public Sponsor(String name, User u){


    }
}
