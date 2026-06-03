package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LearningCourse {
    @EmbeddedId
    LearningCourseID id;
    @ManyToOne
    @MapsId("courseID")
    @JoinColumn(name = "courseID")
    Course course;
    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "userID")
    Account user;
    @CreationTimestamp
    Date startTime;
    String Status;
}