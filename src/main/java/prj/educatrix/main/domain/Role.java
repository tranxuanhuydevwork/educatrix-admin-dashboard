package prj.educatrix.main.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "role_id")
    String role_id;

    String description;
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Account> accounts;


    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
