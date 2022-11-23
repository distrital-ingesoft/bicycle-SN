package com.cyship.wall.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Wall {
    @Id
    @GeneratedValue

    Integer id;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Post> posts;
}
