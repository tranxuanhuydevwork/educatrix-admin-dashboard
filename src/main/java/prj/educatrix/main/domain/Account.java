package prj.educatrix.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    String id;
    String username;
    String password;
    String email;
    String status;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date lastLogin;

    @OneToOne
    Profile profile;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "account_course",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> courses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Reviews> reviews;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", role=" + role.getRole_id() +
                '}';
    }


}