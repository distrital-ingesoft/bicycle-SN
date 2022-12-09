package com.cyship.sponsor.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Award {
    @Id
    String id;
    String type;
    Integer quantity;
    String description;
    @ManyToOne
    @JoinColumn(name = "sponsor_id")
    Sponsor sponsor;

}
