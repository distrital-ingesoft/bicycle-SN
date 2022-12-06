package com.cyship.user.model;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Profile {
    @Id @GeneratedValue
    Integer profileId;
    String firstName;
    String lastName;
    Date birthDate;
    String country;
    String city;
    String avatar;
    String training;
    Integer accountType;

}
