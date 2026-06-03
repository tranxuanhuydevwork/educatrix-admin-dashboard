package prj.educatrix.main.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import prj.educatrix.main.domain.Account;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @MapsId
    @OneToOne
    @JoinColumn
    Account account;

    String displayName;
    @OneToOne
    File avatar;
    String description;
    String name;
    String email;
    String gender;
    LocalDate dob;
    String phone;
}
