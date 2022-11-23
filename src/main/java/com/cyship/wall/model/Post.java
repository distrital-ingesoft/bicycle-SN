package com.cyship.wall.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Post {

    @Id
    @GeneratedValue

    String name;
    Date hour;
    Date date;
    String message;
}
