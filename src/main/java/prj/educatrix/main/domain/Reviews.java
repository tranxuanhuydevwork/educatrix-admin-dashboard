package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    Course course;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    String comment;
    double rating_stars;
}
