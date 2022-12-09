package com.cyship.wall.model;

import com.cyship.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Post {

    @Id
    String postId;
    String title;
    LocalTime hour;
    Date date;
    String message;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


}
