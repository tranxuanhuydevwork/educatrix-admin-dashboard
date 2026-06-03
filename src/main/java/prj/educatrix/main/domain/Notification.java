package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
public class Notification {

    public enum NotificationStatus {
        READ, UNREAD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    private String type;
    private String message;
    private String iconClass;

    @Column(name = "reference_id")
    private String referenceId;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date readAt;
}