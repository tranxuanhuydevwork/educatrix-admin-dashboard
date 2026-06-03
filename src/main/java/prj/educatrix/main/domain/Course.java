package prj.educatrix.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.ToString;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = "categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"categories"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    Account teacher;

    String courseName;
    String description;
    Long price;
    String level;
    double rateting;
    int totalEnrolled;
    String imageUrl;
    int totalReviewed;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedAt;

    String status;

    @ManyToMany
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    @JsonIgnore
    Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    Set<Account> accounts;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Order> orders;

    private static final NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));

    public String getFormattedPrice() {
        return format.format(price);
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Course_Statistics> courseStatistics;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Lesson> lessons;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Reviews> reviews;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String formatDate(Date date) {
        return sdf.format(date);
    }

    @JsonProperty("formattedCreateOn")
    public String getCreatedOnFormatted() {
        return formatDate(createdOn);
    }

    public String getUpdatedAtFormatted() {
        return formatDate(updatedAt);
    }

    public Course_Statistics getLatestStatisticsForPeriod(String period) {
        if (courseStatistics == null || courseStatistics.isEmpty()) {
            return null;
        }

        return courseStatistics.stream()
                .filter(stats -> period.equals(stats.getPeriod()))
                .max(Comparator.comparing(Course_Statistics::getRecordedAt))
                .orElse(null);
    }

    public List<Course_Statistics> getCourseStatistics() {
        return courseStatistics;
    }

    public void setCourseStatistics(List<Course_Statistics> courseStatistics) {
        this.courseStatistics = courseStatistics;
    }
}