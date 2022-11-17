package com.cyship.user.model;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Profile {
    @Id @GeneratedValue
    Integer profileId;

}
