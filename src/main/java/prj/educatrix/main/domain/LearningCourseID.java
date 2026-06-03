package prj.educatrix.main.domain;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;
@Data
@Embeddable
public class LearningCourseID implements Serializable {

    @Column(name = "course_id")
    private Integer courseID;

    @Column(name = "account_id")
    private String userID;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearningCourseID that = (LearningCourseID) o;
        return Objects.equals(courseID, that.courseID) && Objects.equals(userID, that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, userID);
    }
}
