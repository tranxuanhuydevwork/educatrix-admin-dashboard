package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import prj.educatrix.main.domain.Course;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "course_statistics")
public class Course_Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Column(name = "num_enrolled_students", nullable = false)
    private Integer numEnrolledStudent;

    @Column(name = "revenue", nullable = false)
    private Integer revenue;

    @Column(name = "completion_rate", nullable = false)
    private Integer completionRate;

    @Column(name = "drop_out_rate", nullable = false)
    private Integer dropOutRate;

    @CreationTimestamp
    @Column(name = "recorded_at")
    private Date recordedAt;

    @Column(name = "period", nullable = false)
    private String period;

    @Column(name = "rating")
    private Double rating;
}