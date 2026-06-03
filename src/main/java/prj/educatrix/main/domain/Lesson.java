package prj.educatrix.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import prj.educatrix.main.domain.File;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //    @NotNull
//    @Size(min = 3, message = "INVALID_LESSON_TITLE")
    String title;
    String content;
    Long duration;
    String lessonType;
    long position;//thu tu bai hoc trong lesson


    @OneToMany
    Set<File> files;
    @JsonIgnore
    @ManyToOne
    Course course;
    @CreationTimestamp
    Date createdOn;
    @UpdateTimestamp
    Date updatedAt;
}