package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import prj.educatrix.main.domain.Account;
import prj.educatrix.main.domain.Lesson;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Lesson lesson;

    @ManyToOne
    Discussion parentDiscussion;

    @ManyToOne
    Account postedBy;
    String content;

    @CreationTimestamp
    Date createdOn;
    @UpdateTimestamp
    Date updatedAt;
}