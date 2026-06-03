package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String previousStatus;
    String newStatus;
    String changeReason;
    String changeTime;
    String imageEvidence;
    @ManyToOne
    Account account;

}
