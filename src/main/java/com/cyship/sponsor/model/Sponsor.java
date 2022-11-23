package com.cyship.sponsor.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    Integer type;

    @OneToMany(fetch = FetchType.LAZY)
    List<Award> awards;
}
