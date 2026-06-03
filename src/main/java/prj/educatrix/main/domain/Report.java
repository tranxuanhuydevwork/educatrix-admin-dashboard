package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reportId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @Column(nullable = false)
    String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    String content;

    @Column(nullable = false)
    String reportType;
}
