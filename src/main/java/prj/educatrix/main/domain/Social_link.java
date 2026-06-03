package prj.educatrix.main.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Social_link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer socialLinkId;
    String linkPath;
    String typeSocialLink;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Profile profile;
}
