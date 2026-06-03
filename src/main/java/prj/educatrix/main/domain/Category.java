package prj.educatrix.main.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"courses"})
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Integer id;

    String categoryName;
    String description;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    @JsonIgnore
    Set<Course> courses;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
