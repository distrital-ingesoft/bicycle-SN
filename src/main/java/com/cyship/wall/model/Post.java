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

    public Post (String msg, User u, String t, long pId){
        message= msg;
        title = t;
        user = u;
        date = Date.valueOf(java.time.LocalDate.now());
        hour = java.time.LocalTime.now();
        postId = String.valueOf(pId);
        System.out.println(
                "Post creado: " +title +" - "+ message + " - " + u.getUserId()
        );
    }
}
